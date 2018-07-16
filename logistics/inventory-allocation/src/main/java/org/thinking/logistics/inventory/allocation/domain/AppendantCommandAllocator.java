package org.thinking.logistics.inventory.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.command.ShipmentCommand;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.domain.support.AppendantSign;
import org.thinking.logistics.services.core.domain.support.CommandStatus;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.StorageClassification;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppendantCommandAllocator extends AbstractAllocator {
    private ShipmentCommand command;

    public AppendantCommandAllocator(ShipmentOrderHeader header, ShipmentCommand command) throws Exception {
        super(header);
        this.command = command;
    }

    @Override
    public void initialize(ShipmentOrderDetail detail) throws Exception {
        detail.setActualQuantity(this.command.getExpectedQuantity().subtract(this.command.getActualQuantity()));

        super.initialize(detail);
    }

    @Override
    public ShipmentCommand acquireCommand(ShipmentOrderDetail detail, Inventory inventory, BigDecimal quantity) throws Exception {
        ShipmentCommand command = super.acquireCommand(detail, inventory, quantity);

        command.setParent(this.command);
        command.setAppendantSign(AppendantSign.APPEND);

        return command;
    }

    @Override
    public void save() {
        //region 追加拣货按作业分组汇总打包任务
//        ListExpression<ShipmentCommand,QOutboundCommand> commands = this.getCommandService().getPath()
        this.getCommandService().getFactory().selectFrom(this.getCommandService().getPath())
            .where(
                this.getCommandService().getPath().packageType.eq(PackageType.REMAINDER),
                this.getCommandService().getPath().commandStatus.eq(CommandStatus.CREATED),
                this.getCommandService().getPath().parent.eq(this.command),
                this.getCommandService().getPath().header.eq(this.getHeader()),
                this.getCommandService().getPath().appendantSign.eq(AppendantSign.APPEND)
            )
            .select(
                this.getCommandService().getPath().location.area.region,
                this.getCommandService().getPath()
            )
            .groupBy(this.getCommandService().getPath().location.area.region)
            .fetch()
            .forEach(tuple -> {

            });
        //endregion
    }

    @Override
    public void allocate() throws Exception {
        if (this.command.getAppendantSign() != AppendantSign.APPENDING) {
            throw CompositeException.getException("追加拣货标识不满足追加处理的要求", this.getHeader(), this.getHeader().getOwner());
        }

        if (this.command.getExpectedQuantity().compareTo(this.command.getActualQuantity()) == 0) {
            return;
        }

        //校验库存处理指令

        this.initialize(this.command.getDetail());

        if (this.command.getDetail().getCasesQuantity().compareTo(BigDecimal.ZERO) > 0) {
            this.getLots().clear();
            this.getLotInventories().clear();
            this.getInventories().clear();
            this.getCommands().clear();

            this.setPackageType(PackageType.WHOLEPIECES);
            this.setAllocationQuantity(this.command.getDetail().getCasesQuantity());

            this.acquireLotInventory(this.command.getDetail());
            this.acquireLot(false);

            this.acquireLocation(this.command.getDetail());

            this.generateCommands(this.command.getDetail(), false);
        }

        if (this.command.getDetail().getRemainderQuantity().compareTo(BigDecimal.ZERO) > 0) {
            this.getLots().clear();
            this.getLotInventories().clear();
            this.getInventories().clear();
            this.getCommands().clear();

            this.setPackageType(PackageType.REMAINDER);
            this.setAllocationQuantity(this.command.getDetail().getRemainderQuantity());

            this.acquireLotInventory(this.command.getDetail());
            this.acquireLot(false);

            if (this.getAllocationQuantity().compareTo(BigDecimal.ZERO) > 0 && this.command.getDetail().getItem().getStorageClassification() == StorageClassification.ALL) {
                this.setAllocationQuantity(this.command.getDetail().getRemainderQuantity());
                this.acquireLot(true);
            }

            this.acquireLocation(this.command.getDetail());

            this.generateCommands(this.command.getDetail(), false);
        }
    }
}