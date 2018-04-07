package org.thinking.logistics.init.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.thinking.logistics.core.domain.ErrorMessage;
import org.thinking.logistics.core.entity.Address;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.entity.support.DispatchType;
import org.thinking.logistics.core.entity.support.OutboundPriority;
import org.thinking.logistics.core.entity.support.SaleType;
import org.thinking.logistics.core.entity.support.TakegoodsMode;
import org.thinking.logistics.core.repository.AddressRepository;
import org.thinking.logistics.core.repository.HeaderRepository;
import org.thinking.logistics.core.service.ParameterService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

public class OutboundSaleBillInitializer extends AbstractBillInitializer {
    @Autowired
    private ParameterService parameterService;

    @Autowired
    private AddressRepository addressRepository;

    public OutboundSaleBillInitializer(HeaderRepository<OutboundHeader> repository, OutboundHeader header) {
        super(repository, header);
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();

        //region 优先级、提货方式转换
        if (this.header.getSaleType() == SaleType.FLITTING) {
            this.header.setPriority(OutboundPriority.OUTBOUND_FLITTING);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.GREEN_CHANNEL) {
            this.header.setPriority(OutboundPriority.GREEN_CHANNEL);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.SELF_SERVICE) {
//            for (OutboundDetail detail : this.header.getDetails()) {
//                equivalentPieces = equivalentPieces.add(detail.getFactQuantity().divide(new BigDecimal(detail.getGoods().getWholePackageQuantity()), RoundingMode.HALF_UP));
//            }

            if (this.header.getGoodsQuantity() <= new BigInteger(this.parameterService.getValue(this.header.getWarehouse(), "PGS_LSTD")).intValue() && this.header.getEquivalentPieces().compareTo(new BigDecimal(this.parameterService.getValue(this.header.getWarehouse(), "JS_LSTD"))) <= 0) {
                this.header.setPriority(OutboundPriority.GREEN_CHANNEL);
                this.header.setTakegoodsModeSwitch(TakegoodsMode.GREEN_CHANNEL);
            } else if (this.header.getGoodsQuantity() > new BigInteger(this.parameterService.getValue(this.header.getWarehouse(), "PGS_ZTZPS")).intValue() || this.header.getEquivalentPieces().compareTo(new BigDecimal(this.parameterService.getValue(this.header.getWarehouse(), "JS_ZTZPS"))) > 0) {
                this.header.setPriority(OutboundPriority.SELF_SERVICE_2_DELIVERY);
                this.header.setTakegoodsModeSwitch(TakegoodsMode.SELF_SERVICE_2_DELIVERY);
            } else {
                this.header.setPriority(OutboundPriority.SELF_SERVICE);
                this.header.setTakegoodsModeSwitch(TakegoodsMode.SELF_SERVICE);
            }
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.CONSIGNMENT) {
            this.header.setPriority(OutboundPriority.CONSIGNMENT);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.INNER_CITY_DELIVERY) {
            this.header.setPriority(OutboundPriority.INNER_CITY_DELIVERY);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.SELF_SERVICE_INVENTORYUP) {
            this.header.setPriority(OutboundPriority.SELF_SERVICE_INVENTORYUP);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.OUTER_CITY_DELIVERY) {
            this.header.setPriority(OutboundPriority.OUTER_CITY_DELIVERY);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.RETAIL_CHAINS) {
            this.header.setPriority(OutboundPriority.RETAIL_CHAINS);
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.FLITTING) {
            this.header.setPriority(OutboundPriority.OUTBOUND_FLITTING);
        } else {
            this.header.setPriority(OutboundPriority.INNER_CITY_DELIVERY);
            this.header.setTakegoodsModeSwitch(TakegoodsMode.INNER_CITY_DELIVERY);
        }
        //endregion

        //region 配送地址
        Address address = this.addressRepository.findByCustomer(this.header.getCustomer());

        if (address == null) {
            throw ErrorMessage.getException("未配置默认配送地址", this.header, this.header.getOwner(), this.header.getCustomer());
        }

        if (address.getDirection() == null) {
            throw ErrorMessage.getException("默认配送地址未配置配送方向", this.header, this.header.getOwner(), this.header.getCustomer());
        }

        this.header.setAddress(address);
        //endregion

        //region 三方业主自动打单
        if (this.header.getOwner().isThirdpart() && this.parameterService.getValue(this.header.getWarehouse(), "TPL_PRINTBILL").equals("N")) {
            this.header.setRecheckbillPrintSign("Y");
            this.header.setRecheckbillPrintClerk("admin");
            this.header.setRecheckbillPrintTime(Date.valueOf(LocalDate.now()));
            this.header.setReportbillPrintSign("Y");
            this.header.setReportbillPrintClerk("admin");
            this.header.setReportbillPrintTime(Date.valueOf(LocalDate.now()));
        }
        //endregion

        //region 生成配送数据
        //endregion

        //region 仅作配送合流（不进行拣货）的单据直接更新作业状态为外复核确认
        //endregion

        //region 自提，绿色通道自动下发
        if (this.parameterService.isEnable(this.header.getWarehouse(), "ZTDDSFZDXF")) {
            if ((this.header.getTakegoodsMode() == TakegoodsMode.SELF_SERVICE || this.header.getTakegoodsMode() == TakegoodsMode.GREEN_CHANNEL) &&
                    (this.parameterService.getValue(this.header.getWarehouse(), "PSHLSHANGP_TYPE").contains(this.header.getCategory().name())) &&
                    this.header.getSaleType() == SaleType.NORMAL_SALE &&
                    this.header.getDispatchType() == DispatchType.AUTOMATIC) {
                //region 系统截单时间处理
                Date currentTime = Date.valueOf(LocalDate.now());
                Date trimTime = Date.valueOf(this.parameterService.getValue(this.header.getWarehouse(), "JD_TIME"));
                if (currentTime.after(trimTime)) {
                    throw ErrorMessage.getException("当前时间大于系统截单时间【" + trimTime.toString() + "】，不允许自动下发", this.header, this.header.getOwner());
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