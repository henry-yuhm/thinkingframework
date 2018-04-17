package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;
import org.thinking.logistics.services.core.entity.Batches;
import org.thinking.logistics.services.core.entity.Inventory;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.bill.SupplementDetail;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAllocator extends BusinessBase implements Allocator {
    protected final OutboundHeader header;

    protected final boolean remainder2Whole;

    protected PackageType packageType;

    protected int allocationQuantity = 0;

    protected ValidPeriodType validPeriodType;

    protected int batchesQuantity = 0;

    protected Map<Batches, BigDecimal> batches;

    protected List<Inventory> inventories;

    @Resource
    private OutboundHeaderRepository headerRepository;

    public AbstractAllocator(OutboundHeader header) {
        this.header = header;
        this.remainder2Whole = this.isEnable(this.header.getWarehouse(), "ZJBZCLH");
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