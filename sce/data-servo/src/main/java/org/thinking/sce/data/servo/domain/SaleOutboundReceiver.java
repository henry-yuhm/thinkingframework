package org.thinking.sce.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.service.document.ShipmentOrderService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundReceiver extends AbstractReceiver {
    private final ShipmentOrderHeader header;

    @Resource
    private ShipmentOrderService orderService;

    public SaleOutboundReceiver(ShipmentOrderHeader header) {
        this.header = header;
    }

    @Override
    public void verify() throws Exception {
    }

    @Override
    public void save() throws Exception {
        this.header.setEquivalentCases(this.header.getDetails().stream().map(detail -> detail.getActualQuantity().divide(detail.getItem().getLargePackageQuantity(), RoundingMode.HALF_UP)).reduce(BigDecimal::add).get());

        this.orderService.getRepository().save(this.header);
    }
}