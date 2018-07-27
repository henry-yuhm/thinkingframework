package org.thinking.sce.inventory.allocator.domain;

import com.querydsl.core.Tuple;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BusinessBase;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.command.QShipmentCommand;
import org.thinking.sce.service.core.domain.command.ReplenishmentCommand;
import org.thinking.sce.service.core.domain.command.ShipmentCommand;
import org.thinking.sce.service.core.domain.common.Item;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.document.QReversionNoteDetail;
import org.thinking.sce.service.core.domain.document.QShipmentOrderDetail;
import org.thinking.sce.service.core.domain.document.QShipmentOrderHeader;
import org.thinking.sce.service.core.domain.document.ShipmentOrderDetail;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.inventory.Inventory;
import org.thinking.sce.service.core.domain.inventory.LotInventory;
import org.thinking.sce.service.core.domain.inventory.OutboundConfiguration;
import org.thinking.sce.service.core.domain.inventory.ShipmentLedger;
import org.thinking.sce.service.core.domain.support.CommandCategory;
import org.thinking.sce.service.core.domain.support.CommandType;
import org.thinking.sce.service.core.domain.support.ItemClass;
import org.thinking.sce.service.core.domain.support.LedgerCategory;
import org.thinking.sce.service.core.domain.support.LedgerSummary;
import org.thinking.sce.service.core.domain.support.LedgerType;
import org.thinking.sce.service.core.domain.support.PackageType;
import org.thinking.sce.service.core.domain.support.PickupMode;
import org.thinking.sce.service.core.domain.support.SaleType;
import org.thinking.sce.service.core.domain.support.ShipmentStatus;
import org.thinking.sce.service.core.domain.support.SplittingGranularity;
import org.thinking.sce.service.core.domain.support.ValidPeriodType;
import org.thinking.sce.service.core.service.command.ReplenishmentCommandService;
import org.thinking.sce.service.core.service.command.ShipmentCommandService;
import org.thinking.sce.service.core.service.document.ReversionNoteService;
import org.thinking.sce.service.core.service.document.ShipmentOrderService;
import org.thinking.sce.service.core.service.inventory.InventoryService;
import org.thinking.sce.service.core.service.inventory.LedgerService;
import org.thinking.sce.service.core.service.inventory.LotInventoryService;
import org.thinking.sce.service.core.service.inventory.OutboundConfigurationService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAllocator extends BusinessBase implements Allocator {
    private final ShipmentOrderHeader header;

    private final boolean remainder2Cases;

    private final boolean newLot;

    private PackageType packageType;

    private BigDecimal allocationQuantity = BigDecimal.ZERO;

    private ValidPeriodType validPeriodType;

    private int lotNumbers = 0;

    private Map<Lot, BigDecimal> lots = new LinkedHashMap<>(16);

    private List<LotInventory> lotInventories = new LinkedList<>();

    private List<Inventory> inventories = new LinkedList<>();

    private List<ShipmentCommand> commands = new LinkedList<>();

    @Resource
    private ShipmentOrderService orderService;

    @Resource
    private LotInventoryService lotInventoryService;

    @Resource
    private OutboundConfigurationService outboundConfigurationService;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private LedgerService<ShipmentLedger, ShipmentOrderHeader> ledgerService;

    @Resource
    private ShipmentCommandService commandService;

    @Resource
    private ReplenishmentCommandService replenishmentCommandService;

    @Resource
    private ReversionNoteService reversionNoteService;

    public AbstractAllocator(ShipmentOrderHeader header) throws Exception {
        this.header = header;
        this.remainder2Cases = this.isEnable(this.header.getWarehouse(), "整件不足出零货");
        this.newLot = this.isEnable(this.header.getWarehouse(), this.packageType == PackageType.WHOLEPIECES ? "整件无要求出新批号" : "零货无要求出新批号");
    }

    @Override
    public void verify() throws Exception {
        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.INITIALIZED) < 0) {
            throw CompositeException.getException("单据未初始化，不能分配批号", this.header, this.header.getOwner());
        }

        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.STAGINGAREA_ALLOCATED) < 0 && (this.header.getSaleType().compareTo(SaleType.INVENTORY_SORTINGOUT) == 0 && this.header.getSaleType().compareTo(SaleType.EMERGENCY_OUTBOUND) == 0)) {
            throw CompositeException.getException("单据出库阶段【" + this.header.getShipmentStatus().toString() + "】不满足批号分配要求，请检查", this.header, this.header.getOwner());
        }
    }

    @Override
    public void initialize(ShipmentOrderDetail detail) throws Exception {
        detail.setCasesQuantity(detail.getActualQuantity().subtract(detail.getActualRemainder()));
        detail.setRemainderQuantity(detail.getActualRemainder());

        //零货出整件处理
        if (detail.getCasesQuantity().compareTo(BigDecimal.ZERO) > 0) {
            if (this.remainder2Cases && detail.getLocation() != null && detail.getLocation().getPackageType() == PackageType.REMAINDER) {
                detail.setCasesQuantity(BigDecimal.ZERO);
                detail.setRemainderQuantity(detail.getActualQuantity());
            }
        }
    }

    @Override
    public void acquireLotInventory(ShipmentOrderDetail detail) throws Exception {
        if (detail.getRequest() == null) {
            this.validPeriodType = ValidPeriodType.ALL;
            this.lotNumbers = 0;
        } else {
            switch (detail.getRequest()) {
                case SINGLE:
                    this.validPeriodType = ValidPeriodType.ALL;
                    this.lotNumbers = 1;
                    break;
                case NEW:
                    this.validPeriodType = ValidPeriodType.NEW;
                    this.lotNumbers = 2;
                    break;
                case SINGLE_NEW:
                    this.validPeriodType = ValidPeriodType.NEW;
                    this.lotNumbers = 1;
                    break;
                case NO_DEMAND:
                    this.validPeriodType = ValidPeriodType.ALL;
                    this.lotNumbers = 2;
                    break;
                case CLEANUP:
                    this.validPeriodType = ValidPeriodType.ALL;
                    this.lotNumbers = 3;
                    break;
            }
        }

        //region 批号库存
        this.lotInventories = this.lotInventoryService.acquire(detail.getItem(), this.lotNumbers == 0 ? detail.getLot() : null, this.validPeriodType);
        //endregion
    }

    @Override
    public void acquireLot(boolean replenishing) throws Exception {
        this.lots.clear();

        if (this.lotInventories.size() == 0) {
            return;
        }

        if (this.allocationQuantity.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        if (replenishing && this.packageType == PackageType.WHOLEPIECES) {
            return;
        }

        int lotNumbers = this.lotInventories.size();
        if (lotNumbers == 0) {
            return;
        }

        BigDecimal firstLotInventory;
        BigDecimal secondLotInventory;
        BigDecimal outboundQuantity;
        int lastIndex = this.lotInventories.size() - 1;

        for (LotInventory first : this.lotInventories) {
            if (this.allocationQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }

            if (replenishing) {
                firstLotInventory = first.getAvailableInventory();
            } else {
                firstLotInventory = this.packageType == PackageType.WHOLEPIECES ? first.getPalletInventory().add(first.getCaseInventory()) : first.getRemainderInventory();
            }

            if (firstLotInventory.compareTo(BigDecimal.ZERO) <= 0) {
                lotNumbers = lotNumbers - 1;
                continue;
            }

            //region 指定批号计算、单批号计算、多批号计算
            if (this.lotNumbers == 0 || this.lotNumbers == 1 || this.lotNumbers == 3) {
                if (firstLotInventory.compareTo(this.allocationQuantity) >= 0) {
                    if (this.lotNumbers == 1 && !(this.validPeriodType.compareTo(ValidPeriodType.ALL) == 0) && first.getValidPeriodType().compareTo(ValidPeriodType.NEW) == 0 && first.getMixedType().compareTo(ValidPeriodType.OLD) == 0) {
                        //单一批号系统按参数确定是否出单一新批号
                        if (!this.newLot) {
                            continue;
                        }
                    }

                    outboundQuantity = this.allocationQuantity;

                    if (replenishing) {
                        this.replenish(first.getItem(), first.getLot(), this.allocationQuantity.subtract(first.getRemainderInventory()));
                    }

                    this.allocationQuantity = BigDecimal.ZERO;

                    this.lots.put(first.getLot(), outboundQuantity);

                    if (!replenishing) {
                        //单批号计算时退出循环
                        if (this.lotNumbers == 1) {
                            break;
                        }
                    }
                } else {
                    //指定批号、单批号计算时找下一批号
                    if (this.lotNumbers == 0 || this.lotNumbers == 1) {
                        continue;
                    }

                    //多批号不足时出可出库存
                    outboundQuantity = replenishing ? first.getRemainderInventory() : firstLotInventory;
                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                    this.lots.put(first.getLot(), outboundQuantity);
                }
            }
            //endregion

            //region 双批号计算
            if (this.lotNumbers == 2) {
                if (lotNumbers == 1) {
                    if (replenishing) {
                        if (firstLotInventory.compareTo(this.allocationQuantity) >= 0) {
                            outboundQuantity = this.allocationQuantity;

                            this.replenish(first.getItem(), first.getLot(), this.allocationQuantity.subtract(first.getRemainderInventory()));

                            this.allocationQuantity = BigDecimal.ZERO;

                            this.lots.put(first.getLot(), outboundQuantity);
                        } else {
                            this.allocationQuantity = this.allocationQuantity.subtract(first.getRemainderInventory());

                            this.lots.put(first.getLot(), first.getRemainderInventory());
                        }
                    } else {
                        outboundQuantity = this.allocationQuantity.min(firstLotInventory);
                        this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                        this.lots.put(first.getLot(), outboundQuantity);

                        break;
                    }
                } else {
                    for (LotInventory second : this.lotInventories.subList(this.lotInventories.indexOf(first) + 1, lastIndex)) {
                        if (this.allocationQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                            break;
                        }

                        if (replenishing) {
                            secondLotInventory = second.getAvailableInventory();
                        } else {
                            secondLotInventory = this.packageType == PackageType.WHOLEPIECES ? second.getPalletInventory().add(second.getCaseInventory()) : second.getRemainderInventory();
                        }

                        if (firstLotInventory.add(secondLotInventory).compareTo(this.allocationQuantity) >= 0) {
                            if (firstLotInventory.compareTo(this.allocationQuantity) >= 0) {
                                //该批号为当前最老批号，出库
                                outboundQuantity = this.allocationQuantity;

                                if (replenishing) {
                                    this.replenish(first.getItem(), first.getLot(), this.allocationQuantity.subtract(first.getRemainderInventory()));
                                }

                                this.allocationQuantity = BigDecimal.ZERO;

                                this.lots.put(first.getLot(), outboundQuantity);
                            } else {
                                if (replenishing) {
                                    //先出老批号
                                    this.lots.put(first.getLot(), first.getRemainderInventory());

                                    //再出次老批号
                                    this.lots.put(second.getLot(), this.allocationQuantity.subtract(first.getRemainderInventory()));

                                    //次老批号补货
                                    this.replenish(second.getItem(), second.getLot(), this.allocationQuantity.subtract(first.getRemainderInventory()).subtract(second.getRemainderInventory()));

                                    this.allocationQuantity = BigDecimal.ZERO;
                                } else {
                                    //先出老批号
                                    outboundQuantity = this.allocationQuantity.min(firstLotInventory);
                                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                                    this.lots.put(first.getLot(), outboundQuantity);

                                    //再出次老批号
                                    outboundQuantity = this.allocationQuantity.min(secondLotInventory);
                                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                                    this.lots.put(second.getLot(), outboundQuantity);
                                }
                            }
                        }

                        if (!replenishing) {
                            if (this.lotInventories.indexOf(second) == lastIndex) {
                                break;
                            }
                        }
                    }

                    if (this.lotInventories.indexOf(first) == lastIndex - 1) {
                        break;
                    }
                }
            }
            //endregion
        }
    }

    @Override
    public void replenish(Item item, Lot lot, BigDecimal quantity) throws Exception {

    }

    @Override
    public void acquireLocation(ShipmentOrderDetail detail) throws Exception {
        if (this.lots.size() == 0) {
            return;
        }

        //region 出库配置
        List<OutboundConfiguration> configurations = this.outboundConfigurationService.acquireConfiguration(this.packageType, this.header, detail);
        if (configurations.size() == 0) {
            throw CompositeException.getException("批号库房出库顺序未设置", this.header, this.header.getOwner());
        }
        //endregion

        //中药根据参数重定义特殊库房出库顺序
        if (this.header.getItemClass() == ItemClass.TRADITIONAL_CHINESE_MEDICINE && this.isEnable(this.header.getWarehouse(), "中药大单从储备库出库") && detail.getActualQuantity().compareTo(detail.getItem().getTcmOutboundQuantity()) >= 0) {
            int pos1 = configurations.indexOf(configurations.stream().filter(cfg -> cfg.getStoreNo().equalsIgnoreCase("LBK")).findAny().get());
            int pos2 = configurations.indexOf(configurations.stream().filter(cfg -> cfg.getStoreNo().equalsIgnoreCase("ZYL")).findAny().get());
            if (pos1 >= 0) {
                configurations.get(pos1).setStoreNo("ZYL");
            }
            if (pos2 >= 0) {
                configurations.get(pos2).setStoreNo("LBK");
            }
        }

        BigDecimal lotQuantity = this.lots.values().stream().reduce(BigDecimal::add).get();

        List<Inventory> inventories;
        for (Map.Entry<Lot, BigDecimal> lot : this.lots.entrySet()) {
            if (lot.getValue().compareTo(BigDecimal.ZERO) == 0) {
                break;
            }

            //按批号库房出库顺序分配出库批号
            for (OutboundConfiguration configuration : configurations) {
                if (configuration.getStoreNo().equalsIgnoreCase("LTK")) {
                    //立体库在途库存
                    inventories = this.inventoryService.acquire(detail.getWarehouse(), detail.getItem(), lot.getKey());

                    for (Inventory inventory : inventories) {
                        inventory.setAvailableOutboundQuantity(inventory.getTransitionalQuantity());

                        this.addInventory(inventory, lot.getValue());

                        if (lot.getValue().compareTo(BigDecimal.ZERO) == 0) {
                            break;
                        }
                    }
                }

                if (lot.getValue().compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }

                inventories = this.inventoryService.acquire(detail.getWarehouse(), detail.getItem(), lot.getKey(), detail.getInventoryState(), configuration.getStoreCategory(), configuration.getStoreNo(), lot.getValue());

                if (inventories == null || inventories.size() == 0) {
                    continue;
                }

                for (Inventory inventory : inventories) {
                    if (inventory.getAvailableOutboundQuantity().compareTo(BigDecimal.ZERO) > 0) {
                        if (this.packageType == PackageType.WHOLEPIECES) {
                            if (detail.getItem().getCases(inventory.getAvailableOutboundQuantity()).compareTo(BigDecimal.ZERO) == 0) {
                                continue;
                            }
                        }

                        this.addInventory(inventory, lot.getValue());
                    }

                    if (lot.getValue().compareTo(BigDecimal.ZERO) == 0) {
                        break;
                    }
                }

                if (lot.getValue().compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
            }
        }

        BigDecimal locationQuantity = BigDecimal.ZERO;
        for (Inventory inventory : this.inventories) {
            locationQuantity = locationQuantity.add(inventory.getAvailableOutboundQuantity());
        }

        //批号库存满足，货位库存不满足报错
        if (lotQuantity.compareTo(BigDecimal.ZERO) > 0 && locationQuantity.compareTo(lotQuantity) < 0) {
            throw CompositeException.getException("货位分配失败，货位总出库数量【" + locationQuantity + "】小于批号总出库数量【" + lotQuantity + "】", this.header, this.header.getOwner(), detail.getItem());
        }
    }

    @Override
    public void appointLocation(ShipmentOrderDetail detail) throws Exception {
        Inventory inventory = this.inventoryService.acquire(this.header.getWarehouse(), detail.getItem(), detail.getLot(), detail.getLocation(), detail.getInventoryState());

        if (this.packageType == PackageType.REMAINDER &&
            detail.getItem().getSplittingGranularity() == SplittingGranularity.MEDIUM_PACKAGE &&
            !inventory.getLocation().getArea().getStoreNo().equalsIgnoreCase("NHK") &&
            !inventory.getLocation().getArea().getStoreNo().equalsIgnoreCase("THK")) {
            inventory.setAvailableOutboundQuantity(inventory.getAvailableOutboundQuantity().divide(detail.getItem().getMediumPackageQuantity(), RoundingMode.FLOOR).multiply(detail.getItem().getMediumPackageQuantity()));
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
    public ShipmentCommand acquireCommand(ShipmentOrderDetail detail, Inventory inventory, BigDecimal quantity) throws Exception {
        ShipmentCommand command = new ShipmentCommand();

        command.setWarehouse(this.header.getWarehouse());
        command.setPackageType(this.packageType);
        if (this.header.getItemClass() == ItemClass.GIFT && this.header.getParent() == null) {
            command.setCommandType(CommandType.GIFT_OUTBOUND);
        } else if (this.header.getSaleType() == SaleType.PURCHASE_RETURN) {
            command.setCommandType(CommandType.PURCHASE_RETURN);
        } else {
            command.setCommandType(CommandType.SALE_OUTBOUND);
        }
        if (command.getCommandType() == CommandType.PURCHASE_RETURN) {
            command.setCommandCategory(CommandCategory.PURCHASE_RETURN);
        } else if (this.header.getItemClass() == ItemClass.GIFT) {
            command.setCommandCategory(CommandCategory.GIFT_OUTBOUND);
        } else if (this.header.getPickupModeSwitch() == PickupMode.GREEN_CHANNEL) {
            command.setCommandCategory(CommandCategory.GREEN_CHANNEL);
        } else if (this.header.getPickupModeSwitch() == PickupMode.SELF_SERVICE || this.header.getPickupModeSwitch() == PickupMode.SELF_SERVICE_STOCKUP) {
            command.setCommandCategory(CommandCategory.SELF_SERVICE_OUTBOUND);
        } else {
            command.setCommandCategory(CommandCategory.NORMAL_OUTBOUND);
        }
        command.setHeader(this.header);
        command.setDetail(detail);
        command.setItem(inventory.getItem());
        command.setLot(inventory.getLot());
        command.setLocation(inventory.getLocation());
        command.setInventoryState(inventory.getInventoryState());
        command.setCreatedQuantity(quantity);
        command.setExpectedQuantity(command.getCreatedQuantity());
        command.setActualQuantity(command.getCreatedQuantity());
        command.setPallet(inventory.getPallet());
        command.setPickingOrder("");

        return command;
    }

    @Override
    public void charge(Inventory inventory) throws Exception {
        this.ledgerService.save(new ShipmentLedger(), inventory, LedgerSummary.OUTBOUND_RELEASING_PREALLOCATION, LedgerType.PREALLOCATION, LedgerCategory.OUTBOUND, this.header, inventory.getAvailableOutboundQuantity());

        if (inventory.getLocation().isAutomatic()) {
            if (inventory.getTransitionalQuantity().compareTo(BigDecimal.ZERO) > 0) {
                this.ledgerService.save(new ShipmentLedger(), inventory, LedgerSummary.OUTBOUND_MINUS_TRANSITION, LedgerType.TRANSITION, LedgerCategory.TRANSITION, this.header, inventory.getAvailableOutboundQuantity().negate());
            } else if (inventory.getAvailableQuantity().subtract(inventory.getAvailableOutboundQuantity()).compareTo(BigDecimal.ZERO) > 0) {
                this.ledgerService.save(new ShipmentLedger(), inventory, LedgerSummary.OUTBOUND_PLUS_TRANSITION, LedgerType.TRANSITION, LedgerCategory.TRANSITION, this.header, inventory.getAvailableQuantity().subtract(inventory.getAvailableOutboundQuantity()));
            }
        }
    }

    @Override
    public void generateCommands(ShipmentOrderDetail detail, boolean directly) throws Exception {
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

                List<ReplenishmentCommand> replenishmentCommands = this.replenishmentCommandService.acquire(inventory.getWarehouse(), inventory.getItem(), inventory.getLot(), inventory.getLocation());
                for (ReplenishmentCommand replenishmentCommand : replenishmentCommands) {
                    if (replenishedQuantity.compareTo(BigDecimal.ZERO) == 0) {
                        break;
                    }

                    if (replenishmentCommand.getAvailableQuantity().compareTo(replenishedQuantity) >= 0) {
                        directQuantity = replenishedQuantity;
                        replenishedQuantity = BigDecimal.ZERO;
                    } else {
                        directQuantity = replenishmentCommand.getAvailableQuantity();
                        replenishedQuantity = replenishedQuantity.subtract(directQuantity);
                    }

                    replenishmentCommand.setAvailableQuantity(replenishmentCommand.getAvailableQuantity().subtract(directQuantity));

                    //region 同商品同批号同货位零货要累加数量
                    Optional<ShipmentCommand> optionalCommand = this.commands.stream().filter(cmd -> cmd.getItem() == inventory.getItem() && cmd.getLot() == inventory.getLot() && cmd.getLocation() == inventory.getLocation() && cmd.getPackageType() == PackageType.REMAINDER && this.packageType == PackageType.REMAINDER).findAny();
                    ShipmentCommand shipmentCommand;
                    if (optionalCommand == null) {
                        shipmentCommand = this.acquireCommand(detail, inventory, directQuantity);
                        shipmentCommand.getCommands().add(replenishmentCommand);
                        this.commands.add(shipmentCommand);
                    } else {
                        shipmentCommand = optionalCommand.get();
                        shipmentCommand.setCreatedQuantity(shipmentCommand.getCreatedQuantity().add(directQuantity));
                        shipmentCommand.setExpectedQuantity(shipmentCommand.getCreatedQuantity());
                        shipmentCommand.setActualQuantity(shipmentCommand.getCreatedQuantity());
                        shipmentCommand.getCommands().add(replenishmentCommand);
                    }
                    //endregion
                }

                if (replenishedQuantity.compareTo(BigDecimal.ZERO) > 0) {
                    throw CompositeException.getException("批号指令生成错误，没有找到可用的补货在途数量", this.header, this.header.getOwner(), detail.getItem());
                }

                this.replenishmentCommandService.getRepository().saveAll(replenishmentCommands);
            }

            this.charge(inventory);
        }

        this.commandService.getRepository().saveAll(this.commands);
    }

    @Override
    public void check() throws Exception {
        StringBuilder message = new StringBuilder();
        QShipmentOrderHeader header = QShipmentOrderHeader.shipmentOrderHeader;
        QShipmentOrderDetail detail = QShipmentOrderDetail.shipmentOrderDetail;
        QShipmentCommand command = QShipmentCommand.shipmentCommand;
        QReversionNoteDetail reversionNoteDetail = QReversionNoteDetail.reversionNoteDetail;

        List<Tuple> tuples = this.orderService.getFactory().selectFrom(header)
            .innerJoin(header.details, detail)
            .where(
                header.eq(this.header),
                detail.original.isTrue()
            )
            .select(detail.item, detail.expectedQuantity.subtract(detail.lessnessQuantity).sum())
            .groupBy(detail.item)
            .orderBy(detail.item.id.asc())
            .fetch();

        for (Tuple tuple : tuples) {
            Item item = Optional.ofNullable(tuple.get(0, Item.class)).orElse(new Item());
            BigDecimal orderQuantity = Optional.ofNullable(tuple.get(1, BigDecimal.class)).orElse(BigDecimal.ZERO);

            BigDecimal commandQuantity = Optional.ofNullable(this.commandService.getFactory().selectFrom(command)
                .where(
                    command.header.eq(this.header),
                    command.item.eq(item),
                    command.commandType.in(CommandType.SALE_OUTBOUND, CommandType.PURCHASE_RETURN, CommandType.GIFT_OUTBOUND)
                )
                .select(command.createdQuantity.sum())
                .fetchOne()
            ).orElse(BigDecimal.ZERO);

            BigDecimal reverseQuantity = Optional.ofNullable(this.reversionNoteService.getFactory().selectFrom(reversionNoteDetail)
                .where(
                    reversionNoteDetail.header.eq(this.header),
                    reversionNoteDetail.item.eq(item)
                )
                .select(reversionNoteDetail.quantity.sum())
                .fetchOne()
            ).orElse(BigDecimal.ZERO);

            if (orderQuantity.compareTo(commandQuantity.add(reverseQuantity)) != 0) {
                message.append(item.toString() + "批号分配数据异常，订单数量【" + orderQuantity + "】、指令数量【" + commandQuantity + "】、冲红数量【" + reverseQuantity + "】，差异数量为【" + orderQuantity.subtract(commandQuantity).subtract(reverseQuantity) + "】");
            }
        }

        if (message.length() > 0) {
            throw CompositeException.getException(message.toString(), this.header, this.header.getOwner());
        }
    }

    @Override
    public void save() {
        if (this.header.getShipmentStatus() == ShipmentStatus.RESEND) {
            this.header.setShipmentStatus(ShipmentStatus.LOT_ALLOCATED);
        } else {
            if (this.header.getDetails().stream().noneMatch(detail -> detail.isOriginal() && detail.getLessnessQuantity().compareTo(BigDecimal.ZERO) > 0)) {
                this.header.setShipmentStatus(ShipmentStatus.LOT_ALLOCATED);
            } else {
                this.header.setShipmentStatus(ShipmentStatus.SUSPENDED);
            }
        }

        this.orderService.getRepository().save(this.header);
    }
}