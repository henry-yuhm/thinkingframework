package org.thinking.logistics.batches.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessAdapter;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;
import org.thinking.logistics.services.core.entity.Batches;
import org.thinking.logistics.services.core.entity.Inventory;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.command.Command;
import org.thinking.logistics.services.core.repository.OutboundHeaderRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAllocator extends BusinessAdapter implements Allocator {
    protected OutboundHeader header;

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
    }

    @Override
    public void verify() throws Exception {
        if (this.header.getStage().compareTo(OutboundStage.INITIALIZED) < 0) {
            throw CompositeException.getException("单据未初始化，不能分配批号", this.header, this.header.getOwner());
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
    public void setCreationQuantity(Command command, BigDecimal quantity) throws Exception {

    }

    @Override
    public void setCommand(Command command, Inventory inventory) throws Exception {

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