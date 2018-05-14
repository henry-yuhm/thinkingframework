package org.thinking.logistics.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.service.documents.OutboundOrderService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundReceiver extends AbstractReceiver {
    private final OutboundOrderHeader header;

    @Resource
    private OutboundOrderService orderService;

    public SaleOutboundReceiver(OutboundOrderHeader header) {
        this.header = header;
    }

    @Override
    public void verify() throws Exception {
    }

    @Override
    public void save() throws Exception {
        this.header.setEquivalentPieces(this.header.getDetails().stream().map(detail -> detail.getFactQuantity().divide(detail.getGoods().getLargePackageQuantity(), RoundingMode.HALF_UP)).reduce(BigDecimal::add).get());

        this.orderService.getRepository().save(this.header);
    }
}