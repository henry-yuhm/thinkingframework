package org.thinking.logistics.order.initializer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.service.AddressService;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundInitiator extends AbstractInitiator {
    @Resource
    private AddressService addressService;

    public SaleOutboundInitiator(ShipmentOrderHeader header) {
        super(header);
    }

    @Override
    public void save() throws Exception {
        //region 优先级、提货方式转换
        if (this.getHeader().getSaleType() == SaleType.FLITTING) {
            this.getHeader().setPriority(OutboundPriority.FLITTING_OUTBOUND);
        } else if (this.getHeader().getPickupMode() == PickupMode.GREEN_CHANNEL) {
            this.getHeader().setPriority(OutboundPriority.GREEN_CHANNEL);
        } else if (this.getHeader().getPickupMode() == PickupMode.SELF_SERVICE) {
            if (this.getHeader().getItemQuantity() <= this.getIntegerParameter(this.getHeader().getWarehouse(), "绿色通道品规数") && this.getHeader().getEquivalentPieces().compareTo(this.getDecimalParameter(this.getHeader().getWarehouse(), "绿色通道件数")) <= 0) {
                this.getHeader().setPriority(OutboundPriority.GREEN_CHANNEL);
                this.getHeader().setPickupModeSwitch(PickupMode.GREEN_CHANNEL);
            } else if (this.getHeader().getItemQuantity() > this.getIntegerParameter(this.getHeader().getWarehouse(), "自提转配送品规数") || this.getHeader().getEquivalentPieces().compareTo(this.getDecimalParameter(this.getHeader().getWarehouse(), "自提转配送件数")) > 0) {
                this.getHeader().setPriority(OutboundPriority.SELF_SERVICE_2_DISTRIBUTION);
                this.getHeader().setPickupModeSwitch(PickupMode.SELF_SERVICE_2_DISTRIBUTION);
            } else {
                this.getHeader().setPriority(OutboundPriority.SELF_SERVICE);
                this.getHeader().setPickupModeSwitch(PickupMode.SELF_SERVICE);
            }
        } else if (this.getHeader().getPickupMode() == PickupMode.CONSIGNMENT) {
            this.getHeader().setPriority(OutboundPriority.CONSIGNMENT);
        } else if (this.getHeader().getPickupMode() == PickupMode.URBAN_DISTRIBUTION) {
            this.getHeader().setPriority(OutboundPriority.URBAN_DISTRIBUTION);
        } else if (this.getHeader().getPickupMode() == PickupMode.SELF_SERVICE_STOCKUP) {
            this.getHeader().setPriority(OutboundPriority.SELF_SERVICE_STOCKUP);
        } else if (this.getHeader().getPickupMode() == PickupMode.SUBURBAN_DISTRIBUTION) {
            this.getHeader().setPriority(OutboundPriority.SUBURBAN_DISTRIBUTION);
        } else if (this.getHeader().getPickupMode() == PickupMode.RETAIL_CHAINS) {
            this.getHeader().setPriority(OutboundPriority.RETAIL_CHAINS);
        } else if (this.getHeader().getPickupMode() == PickupMode.FLITTING) {
            this.getHeader().setPriority(OutboundPriority.FLITTING_OUTBOUND);
        } else {
            this.getHeader().setPriority(OutboundPriority.URBAN_DISTRIBUTION);
            this.getHeader().setPickupModeSwitch(PickupMode.URBAN_DISTRIBUTION);
        }
        //endregion

        //region 配送地址
        this.getHeader().setAddress(this.addressService.acquire(this.getHeader().getCustomer(), true));
        //endregion

        //region 三方业主自动打单
        if (this.getHeader().getOwner().isThirdpart() && !this.isEnable(this.getHeader().getWarehouse(), "三方业主自动打单")) {
            this.getHeader().setRecheckbillPrintSign(PrintSign.CONFIRMATION);
            this.getHeader().setRecheckbillPrintClerk(this.getOperator());
            this.getHeader().setRecheckbillPrintTime(Date.valueOf(LocalDate.now()));
            this.getHeader().setReportbillPrintSign(PrintSign.CONFIRMATION);
            this.getHeader().setReportbillPrintClerk(this.getOperator());
            this.getHeader().setReportbillPrintTime(Date.valueOf(LocalDate.now()));
        }
        //endregion

        //region 生成配送数据
        //endregion

        //region 仅作配送合流（不进行拣货）的单据直接更新作业状态为外复核确认
        //endregion

        //region 自提，绿色通道自动下发
        if (this.isEnable(this.getHeader().getWarehouse(), "自提订单自动下发")) {
            if ((this.getHeader().getPickupMode() == PickupMode.SELF_SERVICE || this.getHeader().getPickupMode() == PickupMode.GREEN_CHANNEL) &&
                (this.getStringParameter(this.getHeader().getWarehouse(), "配送合流单据类别").contains(this.getHeader().getCategory().name())) &&
                this.getHeader().getSaleType() == SaleType.NORMAL_SALE &&
                this.getHeader().getDispatcherType() == DispatcherType.AUTOMATIC) {
                //region 系统截单时间处理
                Date currentTime = Date.valueOf(LocalDate.now());
                Date trimTime = this.getDateParameter(this.getHeader().getWarehouse(), "截单时间");
                if (currentTime.after(trimTime)) {
                    throw CompositeException.getException("当前时间大于系统截单时间【" + trimTime.toString() + "】，不允许自动下发", this.getHeader(), this.getHeader().getOwner());
                }
                //endregion

                //region 自提、绿色通道按单下发
                //endregion
            }
        }
        //endregion

        super.save();
    }
}