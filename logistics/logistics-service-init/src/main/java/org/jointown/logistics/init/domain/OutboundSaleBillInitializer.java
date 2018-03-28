package org.jointown.logistics.init.domain;

import org.jointown.logistics.core.domain.ErrorMessage;
import org.jointown.logistics.core.entity.Address;
import org.jointown.logistics.core.entity.bill.OutboundSaleBillDetail;
import org.jointown.logistics.core.entity.bill.OutboundSaleBillHeader;
import org.jointown.logistics.core.entity.support.BillPriority;
import org.jointown.logistics.core.entity.support.SaleType;
import org.jointown.logistics.core.entity.support.TakegoodsMode;
import org.jointown.logistics.core.repository.AddressRepository;
import org.jointown.logistics.core.repository.BillHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Map;

public class OutboundSaleBillInitializer extends AbstractBillInitializer<OutboundSaleBillHeader> {
    @Autowired
    private Map<String, String> parameters;

    @Autowired
    private AddressRepository addressRepository;

    public OutboundSaleBillInitializer(BillHeaderRepository<OutboundSaleBillHeader> repository, long id) {
        super(repository, repository.findOne(id));
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();

        //region 优先级、提货方式转换
        if (this.getHeader().getSaleType() == SaleType.FLITTING) {
            this.getHeader().setPriority(BillPriority.OUTBOUND_FLITTING);
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.GREEN_CHANNEL) {
            this.getHeader().setPriority(BillPriority.GREEN_CHANNEL);
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.SELF_SERVICE) {
            int goodsNumber = this.getHeader().getDetails().size();

            BigDecimal equivalentPieces = BigDecimal.ZERO;
            for (OutboundSaleBillDetail detail : this.getHeader().getDetails()) {
                equivalentPieces = equivalentPieces.add(detail.getFactQuantity().divide(new BigDecimal(detail.getGoods().getWholePackageQuantity()), RoundingMode.HALF_UP));
            }

            if (goodsNumber <= new BigInteger(parameters.get("PGS_LSTD")).intValue() && equivalentPieces.compareTo(new BigDecimal(parameters.get("JS_LSTD"))) <= 0) {
                this.getHeader().setPriority(BillPriority.GREEN_CHANNEL);
                this.getHeader().setTakegoodsModeSwitch(TakegoodsMode.GREEN_CHANNEL);
            } else if (goodsNumber > new BigInteger(parameters.get("PGS_ZTZPS")).intValue() || equivalentPieces.compareTo(new BigDecimal(parameters.get("JS_ZTZPS"))) > 0) {
                this.getHeader().setPriority(BillPriority.SELF_SERVICE_2_DELIVERY);
                this.getHeader().setTakegoodsModeSwitch(TakegoodsMode.SELF_SERVICE_2_DELIVERY);
            } else {
                this.getHeader().setPriority(BillPriority.SELF_SERVICE);
                this.getHeader().setTakegoodsModeSwitch(TakegoodsMode.SELF_SERVICE);
            }
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.CONSIGNMENT) {
            this.getHeader().setPriority(BillPriority.CONSIGNMENT);
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.INNER_CITY_DELIVERY) {
            this.getHeader().setPriority(BillPriority.INNER_CITY_DELIVERY);
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.SELF_SERVICE_STOCKUP) {
            this.getHeader().setPriority(BillPriority.SELF_SERVICE_STOCKUP);
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.OUTER_CITY_DELIVERY) {
            this.getHeader().setPriority(BillPriority.OUTER_CITY_DELIVERY);
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.RETAIL_CHAINS) {
            this.getHeader().setPriority(BillPriority.RETAIL_CHAINS);
        } else if (this.getHeader().getTakegoodsMode() == TakegoodsMode.FLITTING) {
            this.getHeader().setPriority(BillPriority.OUTBOUND_FLITTING);
        } else {
            this.getHeader().setPriority(BillPriority.INNER_CITY_DELIVERY);
            this.getHeader().setTakegoodsModeSwitch(TakegoodsMode.INNER_CITY_DELIVERY);
        }
        //endregion

        //region 配送地址
        Address address = this.addressRepository.findByCustomer(this.getHeader().getCustomer());

        if (address == null) {
            throw ErrorMessage.getException("未配置默认配送地址", this.getHeader(), this.getHeader().getOwner(), this.getHeader().getCustomer());
        }

        if (address.getDirection() == null) {
            throw ErrorMessage.getException("默认配送地址未配置配送方向", this.getHeader(), this.getHeader().getOwner(), this.getHeader().getCustomer());
        }

        this.getHeader().setAddress(address);
        //endregion

        //region 三方业主自动打单
        if (this.getHeader().getOwner().isThirdpart() && parameters.get("TPL_PRINTBILL").equals("N")) {
            this.getHeader().setRecheckBillPrintSign("Y");
            this.getHeader().setRecheckBillPrintClerk("admin");
            this.getHeader().setRecheckBillPrintTime(Time.valueOf(LocalTime.now()));
            this.getHeader().setReportBillPrintSign("Y");
            this.getHeader().setReportBillPrintClerk("admin");
            this.getHeader().setReportBillPrintTime(Time.valueOf(LocalTime.now()));
        }
        //endregion

        this.getRepository().save(this.getHeader());
    }
}