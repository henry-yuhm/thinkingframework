package org.thinking.logistics.services.core.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.support.BillCategory;
import org.thinking.logistics.services.core.domain.support.PackageType;
import org.thinking.logistics.services.core.domain.support.SaleType;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.Warehouse;
import org.thinking.logistics.services.core.entity.inventory.OutboundConfiguration;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.LinkedList;

@Repository
public interface OutboundConfigurationRepository extends JpaRepository<OutboundConfiguration, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select cfg from OutboundConfiguration cfg " +
        "where cfg.warehouse = :warehouse " +
        "and cfg.owner = :owner " +
        "and cfg.packageType = :packageType " +
        "and cfg.category = :category " +
        "and cfg.saleType = :saleType " +
        "order by case when :quantity >= cfg.threshold then cfg.lowerOrder else cfg.upperOrder end")
    LinkedList<OutboundConfiguration> acquireConfiguration(Warehouse warehouse, Owner owner, PackageType packageType, BillCategory category, SaleType saleType, BigDecimal quantity);
}