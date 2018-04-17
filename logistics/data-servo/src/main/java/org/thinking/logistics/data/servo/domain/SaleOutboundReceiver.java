package org.thinking.logistics.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaleOutboundReceiver extends AbstractReceiver {
    private final OutboundHeader header;

    @Resource
    private OutboundHeaderRepository repository;

    public SaleOutboundReceiver(OutboundHeader header) {
        this.header = header;
    }

    @Override
    public void verify() throws Exception {
    }

    @Override
    public void save() throws Exception {
        this.header.setEquivalentPieces(this.header.getDetails().stream().map(detail -> detail.getFactQuantity().divide(new BigDecimal(detail.getGoods().getWholePackageQuantity()), RoundingMode.HALF_UP)).reduce(BigDecimal::add).get());

        this.repository.save(this.header);
    }
}