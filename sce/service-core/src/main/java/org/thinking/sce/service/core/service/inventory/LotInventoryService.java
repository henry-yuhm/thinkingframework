package org.thinking.sce.service.core.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.common.Item;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.inventory.LotInventory;
import org.thinking.sce.service.core.domain.inventory.QLotInventory;
import org.thinking.sce.service.core.domain.support.ValidPeriodType;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@Service
public class LotInventoryService extends DomainService<QLotInventory, LotInventory, Long> {
    @Autowired
    public LotInventoryService(EntityManager entityManager, DomainRepository<LotInventory, Long> repository) {
        super(entityManager, repository, QLotInventory.lotInventory);
    }

    public final List<LotInventory> acquire(Item item) {
        return this.acquire(item, null, ValidPeriodType.ALL);
    }

    public final List<LotInventory> acquire(Item item, Lot lot) {
        return this.acquire(item, lot, ValidPeriodType.ALL);
    }

    public final List<LotInventory> acquire(Item item, Lot lot, ValidPeriodType validPeriodType) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().item.eq(item),
                lot == null ? this.getPath().lot.isNotNull() : this.getPath().lot.eq(lot),
                validPeriodType == ValidPeriodType.ALL ? this.getPath().validPeriodType.isNotNull() : this.getPath().validPeriodType.eq(validPeriodType)
            )
            .orderBy(
                this.getPath().validPeriodType.asc(),
                this.getPath().lot.productionDate.asc(),
                this.getPath().lot.id.asc()
            )
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }
}