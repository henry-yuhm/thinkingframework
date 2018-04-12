package org.thinking.logistics.init.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.CompositeException;
import org.thinking.logistics.core.domain.support.DispatcherKind;
import org.thinking.logistics.core.domain.support.OutboundPriority;
import org.thinking.logistics.core.domain.support.SaleKind;
import org.thinking.logistics.core.domain.support.TakegoodsMode;
import org.thinking.logistics.core.entity.Address;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.repository.AddressRepository;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundSaleInitializer extends AbstractInitializer {
    @Resource
    private AddressRepository addressRepository;

    public OutboundSaleInitializer(OutboundHeader header) {
        super(header);
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();

        //region 优先级、提货方式转换
        if (this.header.getSaleKind() == SaleKind.FLITTING) {
            this.header.setPriority(OutboundPriority.FLITTING_OUTBOUND);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.GREEN_CHANNEL) {
            this.header.setPriority(OutboundPriority.GREEN_CHANNEL);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.SELF_SERVICE) {
//            for (OutboundDetail detail : this.header.getDetails()) {
//                equivalentPieces = equivalentPieces.add(detail.getFactQuantity().divide(new BigDecimal(detail.getGoods().getWholePackageQuantity()), RoundingMode.HALF_UP));
//            }

            if (this.header.getGoodsQuantity() <= this.getIntegerParameter(this.header.getWarehouse(), "PGS_LSTD") && this.header.getEquivalentPieces().compareTo(this.getDecimalParameter(this.header.getWarehouse(), "JS_LSTD")) <= 0) {
                this.header.setPriority(OutboundPriority.GREEN_CHANNEL);
                this.header.setTakegoodsModeSwitch(TakegoodsMode.GREEN_CHANNEL);
            } else if (this.header.getGoodsQuantity() > this.getIntegerParameter(this.header.getWarehouse(), "PGS_ZTZPS") || this.header.getEquivalentPieces().compareTo(this.getDecimalParameter(this.header.getWarehouse(), "JS_ZTZPS")) > 0) {
                this.header.setPriority(OutboundPriority.SELF_SERVICE_2_DISTRIBUTION);
                this.header.setTakegoodsModeSwitch(TakegoodsMode.SELF_SERVICE_2_DISTRIBUTION);
            } else {
                this.header.setPriority(OutboundPriority.SELF_SERVICE);
                this.header.setTakegoodsModeSwitch(TakegoodsMode.SELF_SERVICE);
            }
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.CONSIGNMENT) {
            this.header.setPriority(OutboundPriority.CONSIGNMENT);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.URBAN_DISTRIBUTION) {
            this.header.setPriority(OutboundPriority.URBAN_DISTRIBUTION);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.SELF_SERVICE_STOCKUP) {
            this.header.setPriority(OutboundPriority.SELF_SERVICE_STOCKUP);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.SUBURBAN_DISTRIBUTION) {
            this.header.setPriority(OutboundPriority.SUBURBAN_DISTRIBUTION);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.RETAIL_CHAINS) {
            this.header.setPriority(OutboundPriority.RETAIL_CHAINS);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.FLITTING) {
            this.header.setPriority(OutboundPriority.FLITTING_OUTBOUND);
        } else {
            this.header.setPriority(OutboundPriority.URBAN_DISTRIBUTION);
            this.header.setTakegoodsModeSwitch(TakegoodsMode.URBAN_DISTRIBUTION);
        }
        //endregion

        //region 配送地址
        Address address = this.addressRepository.findByCustomer(this.header.getCustomer());

        if (address == null) {
            throw CompositeException.getException("未配置默认配送地址", this.header, this.header.getOwner(), this.header.getCustomer());
        }

        if (address.getDirection() == null) {
            throw CompositeException.getException("默认配送地址未配置配送方向", this.header, this.header.getOwner(), this.header.getCustomer());
        }

        this.header.setAddress(address);
        //endregion

        //region 三方业主自动打单
        if (this.header.getOwner().isThirdpart() && !this.isEnable(this.header.getWarehouse(), "TPL_PRINTBILL")) {
            this.header.setRecheckbillPrintSign("Y");
            this.header.setRecheckbillPrintClerk(this.operator);
            this.header.setRecheckbillPrintTime(Date.valueOf(LocalDate.now()));
            this.header.setReportbillPrintSign("Y");
            this.header.setReportbillPrintClerk(this.operator);
            this.header.setReportbillPrintTime(Date.valueOf(LocalDate.now()));
        }
        //endregion

        //region 生成配送数据
        //endregion

        //region 仅作配送合流（不进行拣货）的单据直接更新作业状态为外复核确认
        //endregion

        //region 自提，绿色通道自动下发
        if (this.isEnable(this.header.getWarehouse(), "ZTDDSFZDXF")) {
            if ((this.header.getTakegoodsMode() == TakegoodsMode.SELF_SERVICE || this.header.getTakegoodsMode() == TakegoodsMode.GREEN_CHANNEL) &&
                    (this.getStringParameter(this.header.getWarehouse(), "PSHLSHANGP_TYPE").contains(this.header.getCategory().name())) &&
                    this.header.getSaleKind() == SaleKind.NORMAL_SALE &&
                    this.header.getDispatcherKind() == DispatcherKind.AUTOMATIC) {
                //region 系统截单时间处理
                Date currentTime = Date.valueOf(LocalDate.now());
                Date trimTime = this.getDateParameter(this.header.getWarehouse(), "JD_TIME");
                if (currentTime.after(trimTime)) {
                    throw CompositeException.getException("当前时间大于系统截单时间【" + trimTime.toString() + "】，不允许自动下发", this.header, this.header.getOwner());
                }
                //endregion

                //region 自提、绿色通道按单下发
                //endregion
            }
        }
        //endregion

        this.save();
    }
}