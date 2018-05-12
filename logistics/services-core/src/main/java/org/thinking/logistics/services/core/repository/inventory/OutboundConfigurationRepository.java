package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.Warehouse;
import org.thinking.logistics.services.core.domain.inventory.OutboundConfiguration;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.LinkedList;

@Repository
public interface OutboundConfigurationRepository extends DomainRepository<OutboundConfiguration, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select cfg from OutboundConfiguration cfg " +
        "where cfg.warehouse = :warehouse " +
        "and cfg.owner = :owner " +
        "and cfg.packageType = :packageType " +
        "and cfg.billCategory = :billCategory " +
        "and cfg.saleType = :saleType " +
        "order by case when :quantity >= cfg.threshold then cfg.lowerOrder else cfg.upperOrder end")
    LinkedList<OutboundConfiguration> acquireConfiguration(Warehouse warehouse, Owner owner, PackageType packageType, BillCategory billCategory, SaleType saleType, BigDecimal quantity);
}