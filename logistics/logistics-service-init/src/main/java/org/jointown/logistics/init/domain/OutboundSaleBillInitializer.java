package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.domain.ErrorMessage;
import org.jointown.logistics.core.entity.Address;
import org.jointown.logistics.core.entity.bill.OutboundDetail;
import org.jointown.logistics.core.entity.bill.OutboundHeader;
import org.jointown.logistics.core.entity.support.OutboundPriority;
import org.jointown.logistics.core.entity.support.SaleType;
import org.jointown.logistics.core.entity.support.TakegoodsMode;
import org.jointown.logistics.core.repository.AddressRepository;
import org.jointown.logistics.core.repository.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Map;

public class OutboundSaleBillInitializer extends AbstractBillInitializer {
    @Autowired
    private Map<String, String> parameters;

    @Autowired
    private AddressRepository addressRepository;

    public OutboundSaleBillInitializer(HeaderRepository<OutboundHeader> repository, long id) {
        super(repository, id);
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
            int goodsNumber = this.header.getDetails().size();

            BigDecimal equivalentPieces = BigDecimal.ZERO;
            for (OutboundDetail detail : this.header.getDetails()) {
                equivalentPieces = equivalentPieces.add(detail.getFactQuantity().divide(new BigDecimal(detail.getGoods().getWholePackageQuantity()), RoundingMode.HALF_UP));
            }

            if (goodsNumber <= new BigInteger(parameters.get("PGS_LSTD")).intValue() && equivalentPieces.compareTo(new BigDecimal(parameters.get("JS_LSTD"))) <= 0) {
                this.header.setPriority(OutboundPriority.GREEN_CHANNEL);
                this.header.setTakegoodsModeSwitch(TakegoodsMode.GREEN_CHANNEL);
            } else if (goodsNumber > new BigInteger(parameters.get("PGS_ZTZPS")).intValue() || equivalentPieces.compareTo(new BigDecimal(parameters.get("JS_ZTZPS"))) > 0) {
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
        } else if (this.header.getTakegoodsMode() == TakegoodsMode.SELF_SERVICE_STOCKUP) {
            this.header.setPriority(OutboundPriority.SELF_SERVICE_STOCKUP);
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
        if (this.header.getOwner().isThirdpart() && parameters.get("TPL_PRINTBILL").equals("N")) {
            this.header.setRecheckbillPrintSign("Y");
            this.header.setRecheckbillPrintClerk("admin");
            this.header.setRecheckbillPrintTime(Time.valueOf(LocalTime.now()));
            this.header.setReportbillPrintSign("Y");
            this.header.setReportbillPrintClerk("admin");
            this.header.setReportbillPrintTime(Time.valueOf(LocalTime.now()));
        }
        //endregion

        this.save();
    }
}