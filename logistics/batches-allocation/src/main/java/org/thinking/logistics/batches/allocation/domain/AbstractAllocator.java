package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Example;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;
import org.thinking.logistics.services.core.entity.Batches;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.bill.SupplementDetail;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;
import org.thinking.logistics.services.core.entity.inventory.BatchesInventory;
import org.thinking.logistics.services.core.entity.inventory.Inventory;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;
import org.thinking.logistics.services.core.repository.inventory.BatchesInventoryRepository;
import org.thinking.logistics.services.core.repository.inventory.InventoryRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAllocator extends BusinessBase implements Allocator {
    protected final OutboundHeader header;

    protected final boolean remainder2Whole;

    protected final boolean newBatches;

    protected PackageType packageType;

    protected BigDecimal allocationQuantity = BigDecimal.ZERO;

    protected ValidPeriodType validPeriodType;

    protected int batchesNumber = 0;

    protected Map<Batches, BigDecimal> batches;

    protected List<BatchesInventory> batchesInventories = new LinkedList<>();

    protected List<Inventory> inventories = new LinkedList<>();

    @Resource
    private OutboundHeaderRepository headerRepository;

    @Resource
    private BatchesInventoryRepository batchesInventoryRepository;

    @Resource
    private InventoryRepository inventoryRepository;

    public AbstractAllocator(OutboundHeader header) {
        this.header = header;
        this.remainder2Whole = this.isEnable(this.header.getWarehouse(), "ZJBZCLH");
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
    public void initialize(OutboundDetail detail) throws Exception {
        detail.setWholeQuantity(detail.getFactQuantity().subtract(detail.getFactRemainder()));
        detail.setRemainderQuantity(detail.getFactRemainder());
    }

    @Override
    public void initialize(SupplementDetail detail) throws Exception {
        detail.setWholeQuantity(detail.getFactQuantity().subtract(detail.getFactRemainder()));
        detail.setRemainderQuantity(detail.getFactRemainder());

        //零货出整件处理
        if (detail.getWholeQuantity().compareTo(BigDecimal.ZERO) > 0) {
            if (this.remainder2Whole && detail.getLocation() != null && detail.getLocation().getPackageType() == PackageType.REMAINDER) {
                detail.setWholeQuantity(BigDecimal.ZERO);
                detail.setRemainderQuantity(detail.getFactQuantity());
            }
        }
    }

    @Override
    public void getBatchesDirectly(OutboundDetail detail) throws Exception {
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
        BatchesInventory.PrimaryKey key = new BatchesInventory.PrimaryKey();
        key.setGoods(detail.getGoods());
        if (this.batchesNumber == 0) {
            key.setBatches(detail.getBatches());
        }
        if (this.validPeriodType.compareTo(ValidPeriodType.ALL) == 0) {
            key.setType(this.validPeriodType);
        }

        BatchesInventory probe = new BatchesInventory();
        probe.setKey(key);

        this.batchesInventories = this.batchesInventoryRepository.findAll(Example.of(probe));
        //endregion

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

            firstBatchesInventory = this.packageType == PackageType.WHOLEPIECES ? first.getPalletInventory().add(first.getWholepiecesInventory()) : first.getRemainderInventory();

            if (firstBatchesInventory.compareTo(BigDecimal.ZERO) <= 0) {
                batchesNumber = batchesNumber - 1;
                continue;
            }

            //region 指定批号计算、单批号计算、多批号计算
            if (this.batchesNumber == 0 || this.batchesNumber == 1 || this.batchesNumber == 3) {
                if (firstBatchesInventory.compareTo(this.allocationQuantity) >= 0) {
                    if (this.batchesNumber == 1 && !(this.validPeriodType.compareTo(ValidPeriodType.ALL) == 0) && first.getKey().getType().compareTo(ValidPeriodType.NEW) == 0 && first.getType().compareTo(ValidPeriodType.OLD) == 0) {
                        //单一批号系统按参数确定是否出单一新批号
                        if (!this.newBatches) {
                            continue;
                        }
                    }

                    outboundQuantity = this.allocationQuantity;
                    this.allocationQuantity = BigDecimal.ZERO;

                    this.batches.put(first.getKey().getBatches(), outboundQuantity);

                    //单批号计算时退出循环
                    if (this.batchesNumber == 1) {
                        break;
                    }
                } else {
                    //指定批号、单批号计算时找下一批号
                    if (this.batchesNumber == 0 || this.batchesNumber == 1) {
                        continue;
                    }

                    //多批号不足时出可出零货库存
                    outboundQuantity = firstBatchesInventory;
                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                    this.batches.put(first.getKey().getBatches(), outboundQuantity);
                }
            }
            //endregion

            //region 双批号计算
            if (this.batchesNumber == 2) {
                if (batchesNumber == 1) {
                    outboundQuantity = this.allocationQuantity.min(firstBatchesInventory);
                    this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                    this.batches.put(first.getKey().getBatches(), outboundQuantity);

                    break;
                } else {
                    for (BatchesInventory second : this.batchesInventories.subList(this.batchesInventories.indexOf(first) + 1, lastIndex)) {
                        if (this.allocationQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                            break;
                        }

                        secondBatchesInventory = this.packageType == PackageType.WHOLEPIECES ? second.getPalletInventory().add(second.getWholepiecesInventory()) : second.getRemainderInventory();

                        if (firstBatchesInventory.add(secondBatchesInventory).compareTo(this.allocationQuantity) >= 0) {
                            if (firstBatchesInventory.compareTo(this.allocationQuantity) >= 0) {
                                //该批号为当前最老批号，出库
                                outboundQuantity = this.allocationQuantity;
                                this.allocationQuantity = BigDecimal.ZERO;

                                this.batches.put(first.getKey().getBatches(), outboundQuantity);
                            } else {
                                //先出老批号
                                outboundQuantity = this.allocationQuantity.min(firstBatchesInventory);
                                this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                                this.batches.put(first.getKey().getBatches(), outboundQuantity);

                                //再出次老批号
                                outboundQuantity = this.allocationQuantity.min(secondBatchesInventory);
                                this.allocationQuantity = this.allocationQuantity.subtract(outboundQuantity);

                                this.batches.put(second.getKey().getBatches(), outboundQuantity);
                            }
                        }

                        if (this.batchesInventories.indexOf(second) == lastIndex) {
                            break;
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
    public void getBatchesIndirectly(OutboundDetail detail) throws Exception {

    }

    @Override
    public void apportionLocation() throws Exception {

    }

    @Override
    public void appointLocation() throws Exception {

    }

    @Override
    public void addInventory() throws Exception {

    }

    @Override
    public void setDetail(OutboundDetail detail) throws Exception {

    }

    @Override
    public int getCommandRows(BigDecimal quantity) throws Exception {
        return 0;
    }

    @Override
    public void setCreationQuantity(OutboundCommand command, BigDecimal quantity) throws Exception {

    }

    @Override
    public void setCommand(OutboundCommand command, Inventory inventory) throws Exception {

    }

    @Override
    public void charge(Inventory inventory) throws Exception {

    }

    @Override
    public void generateCommandsDirectly(List list) throws Exception {

    }

    @Override
    public void generateCommandsIndirectly(List list) throws Exception {

    }

    @Override
    public void check() throws Exception {

    }

    @Override
    public void save() {

    }
}