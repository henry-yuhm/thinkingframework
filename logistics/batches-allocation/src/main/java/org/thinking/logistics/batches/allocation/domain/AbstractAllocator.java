package org.thinking.logistics.batches.allocation.domain;

import com.querydsl.core.Tuple;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.Goods;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;
import org.thinking.logistics.services.core.domain.command.QOutboundCommand;
import org.thinking.logistics.services.core.domain.command.ReplenishingCommand;
import org.thinking.logistics.services.core.domain.documents.*;
import org.thinking.logistics.services.core.domain.inventory.BatchesInventory;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.domain.inventory.OutboundConfiguration;
import org.thinking.logistics.services.core.domain.inventory.OutboundOrderLedger;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.service.command.OutboundCommandService;
import org.thinking.logistics.services.core.service.command.ReplenishingCommandService;
import org.thinking.logistics.services.core.service.documents.InverseOrderService;
import org.thinking.logistics.services.core.service.documents.OutboundOrderService;
import org.thinking.logistics.services.core.service.inventory.BatchesInventoryService;
import org.thinking.logistics.services.core.service.inventory.InventoryService;
import org.thinking.logistics.services.core.service.inventory.LedgerService;
import org.thinking.logistics.services.core.service.inventory.OutboundConfigurationService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAllocator extends BusinessBase implements Allocator {
    private final OutboundOrderHeader header;

    private final boolean remainder2Wholepieces;

    private final boolean newBatches;

    private PackageType packageType;

    private BigDecimal allocationQuantity = BigDecimal.ZERO;

    private ValidPeriodType validPeriodType;

    private int batchesNumber = 0;

    private Map<Batches, BigDecimal> batches = new LinkedHashMap<>();

    private List<BatchesInventory> batchesInventories = new LinkedList<>();

    private List<Inventory> inventories = new LinkedList<>();

    private List<OutboundCommand> commands = new LinkedList<>();

    @Resource
    private OutboundOrderService orderService;

    @Resource
    private BatchesInventoryService batchesInventoryService;

    @Resource
    private OutboundConfigurationService outboundConfigurationService;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private LedgerService<OutboundOrderLedger, OutboundOrderHeader> ledgerService;

    @Resource
    private OutboundCommandService commandService;

    @Resource
    private ReplenishingCommandService replenishingCommandService;

    @Resource
    private InverseOrderService inverseOrderService;

    public AbstractAllocator(OutboundOrderHeader header) throws Exception {
        this.header = header;
        this.remainder2Wholepieces = this.isEnable(this.header.getWarehouse(), "ZJBZCLH");
        this.newBatches = this.isEnable(this.header.getWarehouse(), this.packageType == PackageType.WHOLEPIECES ? "ZJWYQCXPH" : "LHWYQCXPH");
    }

    @Override
    public void verify() throws Exception {
        if (this.header.getStage().compareTo(OutboundStage.INITIALIZED) < 0) {
            throw CompositeException.getException("单据未初始化，不能分配批号", this.header, this.header.getOwner());
        }

        if (this.header.getStage().compareTo(OutboundStage.STAGINGAREA_ALLOCATED) < 0 && (this.header.getSaleType().compareTo(SaleType.INVENTORY_SORTINGOUT) == 0 && this.header.getSaleType().compareTo(SaleType.EMERGENCY_OUTBOUND) == 0)) {
            throw CompositeException.getException("单据作业状态【" + this.header.getStage().name() + "】不满足批号分配要求，请检查", this.header, this.header.getOwner());
        }
    }

    @Override
    public void initialize(OutboundOrderDetail detail) throws Exception {
        detail.setWholepiecesQuantity(detail.getFactQuantity().subtract(detail.getFactRemainder()));
        detail.setRemainderQuantity(detail.getFactRemainder());

        //零货出整件处理
        if (detail.getWholepiecesQuantity().compareTo(BigDecimal.ZERO) > 0) {
            if (this.remainder2Wholepieces && detail.getLocation() != null && detail.getLocation().getPackageType() == PackageType.REMAINDER) {
                detail.setWholepiecesQuantity(BigDecimal.ZERO);
                detail.setRemainderQuantity(detail.getFactQuantity());
            }
        }
    }

    @Override
    public void acquireBatchesInventory(OutboundOrderDetail detail) throws Exception {
        if (detail.getRequest() == null) {
            this.validPeriodType = ValidPeriodType.ALL;
            this.batchesNumber = 0;
        } else {
            switch (detail.getRequest()) {
                case SINGLE:
                    this.validPeriodType = ValidPeriodType.ALL;
                    this.batchesNumber = 1;
                    break;
                case NEW:
                    this.validPeriodType = ValidPeriodType.NEW;
                    this.batchesNumber = 2;
                    break;
                case SINGLE_NEW:
                    this.validPeriodType = ValidPeriodType.NEW;
                    this.batchesNumber = 1;
                    break;
                case NO_DEMAND:
                    this.validPeriodType = ValidPeriodType.ALL;
                    this.batchesNumber = 2;
                    break;
                case CLEANUP:
                    this.validPeriodType = ValidPeriodType.ALL;
                    this.batchesNumber = 3;
                    break;
            }
        }

        //region 批号库存
        this.batchesInventories = this.batchesInventoryService.acquire(detail.getGoods(), this.batchesNumber == 0 ? detail.getBatches() : null, this.validPeriodType);
        //endregion
    }

    @Override
    public void acquireBatches(boolean replenishing) throws Exception {
        this.batches.clear();

        if (this.batchesInventories.size() == 0) {
            return;
        }

        if (this.allocationQuantity.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        if (replenishing && this.packageType == PackageType.WHOLEPIECES) {
            return;
        }

        int batchesNumber = this.batchesInventories.size();
        if (batchesNumber == 0) {
            return;
        }

        BigDecimal firstBatchesInventory;
        BigDecimal secondBatchesInventory;
        BigDecimal outboundQuantity;
        int lastIndex = this.batchesInventories.size() - 1;

        for (BatchesInventory first : this.batchesInventories) {
            if (this.allocationQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }

            if (replenishing) {
                firstBatchesInventory = first.getAvailableInventory();
            } else {
                firstBatchesInventory = this.packageType == PackageType.WHOLEPIECES ? first.getPalletInventory().add(first.getWholepiecesInventory()) : first.getRemainderInventory();
            }

            if (firstBatchesInventory.compareTo(BigDecimal.ZERO) <= 0) {
                batchesNumber = batchesNumber - 1;
                continue;
            }

            //region 指定批号计算、单批号计算、多批号计算
            if (this.batchesNumber == 0 || this.batchesNumber == 1 || this.batchesNumber == 3) {
                if (firstBatchesInventory.compareTo(this.allocationQuantity) >= 0) {
                    if (this.batchesNumber == 1 && !(this.validPeriodType.compareTo(ValidPeriodType.ALL) == 0) && first.getType().compareTo(ValidPeriodType.NEW) == 0 && first.getMixedType().compareTo(ValidPeriodType.OLD) == 0) {
                        //单一批号系统按参数确定是否出单一新批号
                        if (!this.newBatches) {
                            continue;
                        }
                    }

                    outboundQuantity = this.allocationQuantity;

                    if (replenishing) {
                        this.replenish(first.getGoods(), first.getBatches(), this.allocationQuantity.subtract(first.getRemainderInventory()));
                    }

                    this.allocationQuantity = BigDecimal.ZERO;

                    this.batches.put(first.getBatches(), outboundQuantity);

                    if (!replenishing) {
                        //单批号计算时退出循环
                        if (this.batchesNumber == 1) {
                            break;
                        }
                    }
                } else {
                    //指定批号、单批号计算时找下一批号
                    if (this.batchesNumber == 0 || this.batchesNumber == 1) {
                        continue;
                    }

                    //多批号不足时出可出库存
                    outboundQuantity = replenishing ? first.getRemainderInventory() : firstBatchesInventory;
                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                    this.batches.put(first.getBatches(), outboundQuantity);
                }
            }
            //endregion

            //region 双批号计算
            if (this.batchesNumber == 2) {
                if (batchesNumber == 1) {
                    if (replenishing) {
                        if (firstBatchesInventory.compareTo(this.allocationQuantity) >= 0) {
                            outboundQuantity = this.allocationQuantity;

                            this.replenish(first.getGoods(), first.getBatches(), this.allocationQuantity.subtract(first.getRemainderInventory()));

                            this.allocationQuantity = BigDecimal.ZERO;

                            this.batches.put(first.getBatches(), outboundQuantity);
                        } else {
                            this.allocationQuantity = this.allocationQuantity.subtract(first.getRemainderInventory());

                            this.batches.put(first.getBatches(), first.getRemainderInventory());
                        }
                    } else {
                        outboundQuantity = this.allocationQuantity.min(firstBatchesInventory);
                        this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                        this.batches.put(first.getBatches(), outboundQuantity);

                        break;
                    }
                } else {
                    for (BatchesInventory second : this.batchesInventories.subList(this.batchesInventories.indexOf(first) + 1, lastIndex)) {
                        if (this.allocationQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                            break;
                        }

                        if (replenishing) {
                            secondBatchesInventory = second.getAvailableInventory();
                        } else {
                            secondBatchesInventory = this.packageType == PackageType.WHOLEPIECES ? second.getPalletInventory().add(second.getWholepiecesInventory()) : second.getRemainderInventory();
                        }

                        if (firstBatchesInventory.add(secondBatchesInventory).compareTo(this.allocationQuantity) >= 0) {
                            if (firstBatchesInventory.compareTo(this.allocationQuantity) >= 0) {
                                //该批号为当前最老批号，出库
                                outboundQuantity = this.allocationQuantity;

                                if (replenishing) {
                                    this.replenish(first.getGoods(), first.getBatches(), this.allocationQuantity.subtract(first.getRemainderInventory()));
                                }

                                this.allocationQuantity = BigDecimal.ZERO;

                                this.batches.put(first.getBatches(), outboundQuantity);
                            } else {
                                if (replenishing) {
                                    //先出老批号
                                    this.batches.put(first.getBatches(), first.getRemainderInventory());

                                    //再出次老批号
                                    this.batches.put(second.getBatches(), this.allocationQuantity.subtract(first.getRemainderInventory()));

                                    //次老批号补货
                                    this.replenish(second.getGoods(), second.getBatches(), this.allocationQuantity.subtract(first.getRemainderInventory()).subtract(second.getRemainderInventory()));

                                    this.allocationQuantity = BigDecimal.ZERO;
                                } else {
                                    //先出老批号
                                    outboundQuantity = this.allocationQuantity.min(firstBatchesInventory);
                                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                                    this.batches.put(first.getBatches(), outboundQuantity);

                                    //再出次老批号
                                    outboundQuantity = this.allocationQuantity.min(secondBatchesInventory);
                                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                                    this.batches.put(second.getBatches(), outboundQuantity);
                                }
                            }
                        }

                        if (!replenishing) {
                            if (this.batchesInventories.indexOf(second) == lastIndex) {
                                break;
                            }
                        }
                    }

                    if (this.batchesInventories.indexOf(first) == lastIndex - 1) {
                        break;
                    }
                }
            }
            //endregion
        }
    }

    @Override
    public void replenish(Goods goods, Batches batches, BigDecimal quantity) throws Exception {

    }

    @Override
    public void acquireLocation(OutboundOrderDetail detail) throws Exception {
        if (this.batches.size() == 0) {
            return;
        }

        //region 出库配置
        List<OutboundConfiguration> configurations = this.outboundConfigurationService.acquireConfiguration(this.packageType, this.header, detail);
        if (configurations.size() == 0) {
            throw CompositeException.getException("批号库房出库顺序未设置", this.header, this.header.getOwner());
        }
        //endregion

        //中药根据参数重定义特殊库房出库顺序
        if (this.header.getCategory() == BillCategory.TRADITIONAL_CHINESE_MEDICINE && this.isEnable(this.header.getWarehouse(), "ZYDJCLBHCK") && detail.getFactQuantity().compareTo(detail.getGoods().getTcmOutboundQuantity()) >= 0) {
            int pos1 = configurations.indexOf(configurations.stream().filter(cfg -> cfg.getStoreNo().equalsIgnoreCase("LBK")).findAny().get());
            int pos2 = configurations.indexOf(configurations.stream().filter(cfg -> cfg.getStoreNo().equalsIgnoreCase("ZYL")).findAny().get());
            if (pos1 >= 0) {
                configurations.get(pos1).setStoreNo("ZYL");
            }
            if (pos2 >= 0) {
                configurations.get(pos2).setStoreNo("LBK");
            }
        }

        BigDecimal batchesQuantity = BigDecimal.ZERO;
        for (BigDecimal quantity : this.batches.values()) {
            batchesQuantity = batchesQuantity.add(quantity);
        }

        List<Inventory> inventories;
        for (Batches batches : this.batches.keySet()) {
            if (this.batches.get(batches).compareTo(BigDecimal.ZERO) == 0) {
                break;
            }

            //按批号库房出库顺序分配出库批号
            for (OutboundConfiguration configuration : configurations) {
                if (configuration.getStoreNo().equalsIgnoreCase("LTK")) {
                    //立体库在途库存
                    inventories = this.inventoryService.acquire(detail.getWarehouse(), detail.getGoods(), batches);

                    for (Inventory inventory : inventories) {
                        inventory.setAvailableOutboundQuantity(inventory.getTransitionalQuantity());

                        this.addInventory(inventory, this.batches.get(batches));

                        if (this.batches.get(batches).compareTo(BigDecimal.ZERO) == 0) {
                            break;
                        }
                    }
                }

                if (this.batches.get(batches).compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }

                inventories = this.inventoryService.acquire(detail.getWarehouse(), detail.getGoods(), batches, detail.getInventoryState(), configuration.getStoreCategory(), configuration.getStoreNo(), this.batches.get(batches));

                if (inventories == null || inventories.size() == 0) {
                    continue;
                }

                for (Inventory inventory : inventories) {
                    if (inventory.getAvailableOutboundQuantity().compareTo(BigDecimal.ZERO) > 0) {
                        if (this.packageType == PackageType.WHOLEPIECES) {
                            if (detail.getGoods().getPieces(inventory.getAvailableOutboundQuantity()).compareTo(BigDecimal.ZERO) == 0) {
                                continue;
                            }
                        }

                        this.addInventory(inventory, this.batches.get(batches));
                    }

                    if (this.batches.get(batches).compareTo(BigDecimal.ZERO) == 0) {
                        break;
                    }
                }

                if (this.batches.get(batches).compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
            }
        }

        BigDecimal locationQuantity = BigDecimal.ZERO;
        for (Inventory inventory : this.inventories) {
            locationQuantity = locationQuantity.add(inventory.getAvailableOutboundQuantity());
        }

        //批号库存满足，货位库存不满足报错
        if (batchesQuantity.compareTo(BigDecimal.ZERO) > 0 && locationQuantity.compareTo(batchesQuantity) < 0) {
            throw CompositeException.getException("货位分配失败，货位总出库数量【" + locationQuantity + "】小于批号总出库数量【" + batchesQuantity + "】", this.header, this.header.getOwner(), detail.getGoods());
        }
    }

    @Override
    public void appointLocation(OutboundOrderDetail detail) throws Exception {
        Inventory inventory = this.inventoryService.acquire(this.header.getWarehouse(), detail.getGoods(), detail.getBatches(), detail.getLocation(), detail.getInventoryState());

        if (this.packageType == PackageType.REMAINDER &&
            detail.getGoods().getSplittingGranularity() == SplittingGranularity.MEDIUM_PACKAGE &&
            !inventory.getLocation().getArea().getStoreNo().equalsIgnoreCase("NHK") &&
            !inventory.getLocation().getArea().getStoreNo().equalsIgnoreCase("THK")) {
            inventory.setAvailableOutboundQuantity(inventory.getAvailableOutboundQuantity().divide(detail.getGoods().getMediumPackageQuantity(), RoundingMode.FLOOR).multiply(detail.getGoods().getMediumPackageQuantity()));
        }

        if (inventory.getAvailableOutboundQuantity().compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        this.addInventory(inventory, this.allocationQuantity);
    }

    @Override
    public void addInventory(Inventory inventory, BigDecimal quantity) throws Exception {
        BigDecimal outboundQuantity;

        if (inventory.getAvailableOutboundQuantity().compareTo(quantity) <= 0) {
            outboundQuantity = inventory.getAvailableOutboundQuantity();
            quantity = quantity.subtract(inventory.getAvailableOutboundQuantity());
        } else {
            outboundQuantity = quantity;
            quantity = BigDecimal.ZERO;
        }

        inventory.setAvailableOutboundQuantity(outboundQuantity);

        this.inventories.add(inventory);
    }

    @Override
    public OutboundCommand acquireCommand(OutboundOrderDetail detail, Inventory inventory, BigDecimal quantity) throws Exception {
        OutboundCommand command = new OutboundCommand();

        command.setWarehouse(this.header.getWarehouse());
        command.setPackageType(this.packageType);
        if (this.header.getCategory() == BillCategory.GIFT && this.header.getParent() == null) {
            command.setCommandType(CommandType.GIFT_OUTBOUND);
        } else if (this.header.getSaleType() == SaleType.PURCHASE_RETURN) {
            command.setCommandType(CommandType.PURCHASE_RETURN);
        } else {
            command.setCommandType(CommandType.SALE_OUTBOUND);
        }
        if (command.getCommandType() == CommandType.PURCHASE_RETURN) {
            command.setCommandCategory(CommandCategory.PURCHASE_RETURN);
        } else if (this.header.getCategory() == BillCategory.GIFT) {
            command.setCommandCategory(CommandCategory.GIFT_OUTBOUND);
        } else if (this.header.getTakegoodsModeSwitch() == TakegoodsMode.GREEN_CHANNEL) {
            command.setCommandCategory(CommandCategory.GREEN_CHANNEL);
        } else if (this.header.getTakegoodsModeSwitch() == TakegoodsMode.SELF_SERVICE || this.header.getTakegoodsModeSwitch() == TakegoodsMode.SELF_SERVICE_STOCKUP) {
            command.setCommandCategory(CommandCategory.SELF_SERVICE_OUTBOUND);
        } else {
            command.setCommandCategory(CommandCategory.NORMAL_OUTBOUND);
        }
        command.setHeader(this.header);
        command.setDetail(detail);
        command.setGoods(inventory.getGoods());
        command.setBatches(inventory.getBatches());
        command.setLocation(inventory.getLocation());
        command.setInventoryState(inventory.getInventoryState());
        command.setCreationQuantity(quantity);
        command.setPlanQuantity(command.getCreationQuantity());
        command.setFactQuantity(command.getCreationQuantity());
        command.setPallet(inventory.getPallet());
        command.setPickingOrder("");

        return command;
    }

    @Override
    public void charge(Inventory inventory) throws Exception {
        this.ledgerService.save(new OutboundOrderLedger(), inventory, LedgerSummary.OUTBOUND_RELEASING_PREALLOCATION, LedgerType.PREALLOCATION, LedgerCategory.OUTBOUND, this.header, inventory.getAvailableOutboundQuantity());

        if (inventory.getLocation().isAutomatic()) {
            if (inventory.getTransitionalQuantity().compareTo(BigDecimal.ZERO) > 0) {
                this.ledgerService.save(new OutboundOrderLedger(), inventory, LedgerSummary.OUTBOUND_MINUS_TRANSITION, LedgerType.TRANSITION, LedgerCategory.TRANSITION, this.header, inventory.getAvailableOutboundQuantity().negate());
            } else if (inventory.getAvailableQuantity().subtract(inventory.getAvailableOutboundQuantity()).compareTo(BigDecimal.ZERO) > 0) {
                this.ledgerService.save(new OutboundOrderLedger(), inventory, LedgerSummary.OUTBOUND_PLUS_TRANSITION, LedgerType.TRANSITION, LedgerCategory.TRANSITION, this.header, inventory.getAvailableQuantity().subtract(inventory.getAvailableOutboundQuantity()));
            }
        }
    }

    @Override
    public void generateCommands(OutboundOrderDetail detail, boolean directly) throws Exception {
        if (this.inventories.size() == 0) {
            return;
        }

        BigDecimal directQuantity;
        BigDecimal replenishedQuantity;
        for (Inventory inventory : this.inventories) {
            if (inventory.getAvailableOutboundQuantity().compareTo(inventory.getPhysicalOutboundQuantity()) <= 0) {
                directQuantity = inventory.getAvailableOutboundQuantity();
                replenishedQuantity = BigDecimal.ZERO;
            } else {
                directQuantity = inventory.getPhysicalOutboundQuantity();
                replenishedQuantity = inventory.getAvailableOutboundQuantity().subtract(inventory.getPhysicalOutboundQuantity());

                if (directly) {
                    replenishedQuantity = BigDecimal.ZERO;
                    inventory.setAvailableOutboundQuantity(inventory.getPhysicalOutboundQuantity());
                }
            }

            if (directQuantity.compareTo(BigDecimal.ZERO) > 0) {
                this.commands.add(this.acquireCommand(detail, inventory, directQuantity));
            }

            if (replenishedQuantity.compareTo(BigDecimal.ZERO) > 0) {
                directQuantity = BigDecimal.ZERO;

                List<ReplenishingCommand> replenishingCommands = this.replenishingCommandService.acquire(inventory.getWarehouse(), inventory.getGoods(), inventory.getBatches(), inventory.getLocation());
                for (ReplenishingCommand replenishingCommand : replenishingCommands) {
                    if (replenishedQuantity.compareTo(BigDecimal.ZERO) == 0) {
                        break;
                    }

                    if (replenishingCommand.getAvailableQuantity().compareTo(replenishedQuantity) >= 0) {
                        directQuantity = replenishedQuantity;
                        replenishedQuantity = BigDecimal.ZERO;
                    } else {
                        directQuantity = replenishingCommand.getAvailableQuantity();
                        replenishedQuantity = replenishedQuantity.subtract(directQuantity);
                    }

                    replenishingCommand.setAvailableQuantity(replenishingCommand.getAvailableQuantity().subtract(directQuantity));

                    //region 同商品同批号同货位零货要累加数量
                    Optional<OutboundCommand> optionalCommand = this.commands.stream().filter(cmd -> cmd.getGoods() == inventory.getGoods() && cmd.getBatches() == inventory.getBatches() && cmd.getLocation() == inventory.getLocation() && cmd.getPackageType() == PackageType.REMAINDER && this.packageType == PackageType.REMAINDER).findAny();
                    OutboundCommand outboundCommand;
                    if (optionalCommand == null) {
                        outboundCommand = this.acquireCommand(detail, inventory, directQuantity);
                        outboundCommand.getCommands().add(replenishingCommand);
                        this.commands.add(outboundCommand);
                    } else {
                        outboundCommand = optionalCommand.get();
                        outboundCommand.setCreationQuantity(outboundCommand.getCreationQuantity().add(directQuantity));
                        outboundCommand.setPlanQuantity(outboundCommand.getCreationQuantity());
                        outboundCommand.setFactQuantity(outboundCommand.getCreationQuantity());
                        outboundCommand.getCommands().add(replenishingCommand);
                    }
                    //endregion
                }

                if (replenishedQuantity.compareTo(BigDecimal.ZERO) > 0) {
                    throw CompositeException.getException("批号指令生成错误，没有找到可用的补货在途数量", this.header, this.header.getOwner(), detail.getGoods());
                }

                this.replenishingCommandService.getRepository().saveAll(replenishingCommands);
            }

            this.charge(inventory);
        }

        this.commandService.getRepository().saveAll(this.commands);
    }

    @Override
    public void check() throws Exception {
        StringBuilder message = new StringBuilder();
        QOutboundOrderHeader header = QOutboundOrderHeader.outboundOrderHeader;
        QOutboundOrderDetail detail = QOutboundOrderDetail.outboundOrderDetail;
        QOutboundCommand command = QOutboundCommand.outboundCommand;
        QInverseOrderDetail inverseDetail = QInverseOrderDetail.inverseOrderDetail;

        List<Tuple> tuples = this.orderService.getFactory().selectFrom(header)
            .innerJoin(header.details, detail)
            .where(
                header.eq(this.header),
                detail.original.isTrue()
            )
            .select(detail.goods, detail.planQuantity.subtract(detail.lessnessQuantity).sum())
            .groupBy(detail.goods)
            .orderBy(detail.goods.id.asc())
            .fetch();

        for (Tuple tuple : tuples) {
            Goods goods = Optional.ofNullable(tuple.get(0, Goods.class)).orElse(new Goods());
            BigDecimal orderQuantity = Optional.ofNullable(tuple.get(1, BigDecimal.class)).orElse(BigDecimal.ZERO);

            BigDecimal commandQuantity = Optional.ofNullable(this.commandService.getFactory().selectFrom(command)
                .where(
                    command.header.eq(this.header),
                    command.goods.eq(goods),
                    command.commandType.in(CommandType.SALE_OUTBOUND, CommandType.PURCHASE_RETURN, CommandType.GIFT_OUTBOUND)
                )
                .select(command.creationQuantity.sum())
                .fetchOne()
            ).orElse(BigDecimal.ZERO);

            BigDecimal inverseQuantity = Optional.ofNullable(this.inverseOrderService.getFactory().selectFrom(inverseDetail)
                .where(
                    inverseDetail.header.eq(this.header),
                    inverseDetail.goods.eq(goods)
                )
                .select(inverseDetail.quantity.sum())
                .fetchOne()
            ).orElse(BigDecimal.ZERO);

            if (orderQuantity.compareTo(commandQuantity.add(inverseQuantity)) != 0) {
                message.append(goods.toString() + "批号分配数据异常，订单数量【" + orderQuantity + "】、指令数量【" + commandQuantity + "】、冲红数量【" + inverseQuantity + "】，差异数量为【" + orderQuantity.subtract(commandQuantity).subtract(inverseQuantity) + "】");
            }
        }

        if (message.length() > 0) {
            throw CompositeException.getException(message.toString(), this.header, this.header.getOwner());
        }
    }

    @Override
    public void save() {
        if (this.header.getStage() == OutboundStage.RESEND) {
            this.header.setStage(OutboundStage.BATCHES_ALLOCATED);
        } else {
            if (this.header.getDetails().stream().noneMatch(detail -> detail.isOriginal() && detail.getLessnessQuantity().compareTo(BigDecimal.ZERO) > 0)) {
                this.header.setStage(OutboundStage.BATCHES_ALLOCATED);
            } else {
                this.header.setStage(OutboundStage.SUSPENDED);
            }
        }

        this.orderService.getRepository().save(this.header);
    }
}