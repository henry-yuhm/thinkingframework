package org.thinking.sce.service.core.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.document.*;
import org.thinking.sce.service.core.domain.inventory.OutboundConfiguration;
import org.thinking.sce.service.core.domain.support.PackageType;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class OutboundConfigurationService extends DomainService<QOutboundConfiguration, OutboundConfiguration, Long> {
    @Autowired
    public OutboundConfigurationService(EntityManager entityManager, DomainRepository<OutboundConfiguration, Long> repository) {
        super(entityManager, repository, QOutboundConfiguration.outboundConfiguration);
    }

    public final List<OutboundConfiguration> acquireConfiguration(PackageType packageType, ShipmentOrderHeader header, ShipmentOrderDetail detail) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(header.getWarehouse()),
                this.getPath().owner.eq(header.getOwner()),
                this.getPath().packageType.eq(packageType),
                this.getPath().itemClass.eq(header.getItemClass()),
                this.getPath().saleType.eq(header.getSaleType()))
            .orderBy(
                this.getPath().threshold.loe(packageType == PackageType.WHOLEPIECES ? detail.getItem().getCases(detail.getCasesQuantity()) : detail.getItem().getRemainder(detail.getRemainderQuantity())).when(true).then(this.getPath().lowerOrder).otherwise(this.getPath().upperOrder).asc())
            .fetch();
    }
}