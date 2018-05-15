package org.thinking.logistics.services.core.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderDetail;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.inventory.OutboundConfiguration;
import org.thinking.logistics.services.core.domain.inventory.QOutboundConfiguration;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class OutboundConfigurationService extends DomainService<QOutboundConfiguration, OutboundConfiguration, Long> {
    @Autowired
    public OutboundConfigurationService(EntityManager entityManager, DomainRepository<OutboundConfiguration, Long> repository) {
        super(entityManager, repository, QOutboundConfiguration.outboundConfiguration);
    }

    public final List<OutboundConfiguration> acquireConfiguration(PackageType packageType, OutboundOrderHeader header, OutboundOrderDetail detail) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(header.getWarehouse()),
                this.getPath().owner.eq(header.getOwner()),
                this.getPath().packageType.eq(packageType),
                this.getPath().billCategory.eq(header.getCategory()),
                this.getPath().saleType.eq(header.getSaleType()))
            .orderBy(
                this.getPath().threshold.loe(packageType == PackageType.WHOLEPIECES ? detail.getGoods().getPieces(detail.getWholepiecesQuantity()) : detail.getGoods().getRemainder(detail.getRemainderQuantity())).when(true).then(this.getPath().lowerOrder).otherwise(this.getPath().upperOrder).asc())
            .fetch();
    }
}