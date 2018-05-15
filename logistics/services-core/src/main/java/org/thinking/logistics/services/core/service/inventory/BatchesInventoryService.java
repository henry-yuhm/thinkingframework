package org.thinking.logistics.services.core.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.Goods;
import org.thinking.logistics.services.core.domain.inventory.BatchesInventory;
import org.thinking.logistics.services.core.domain.inventory.QBatchesInventory;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@Service
public class BatchesInventoryService extends DomainService<QBatchesInventory, BatchesInventory, Long> {
    @Autowired
    public BatchesInventoryService(EntityManager entityManager, DomainRepository<BatchesInventory, Long> repository) {
        super(entityManager, repository, QBatchesInventory.batchesInventory);
    }

    public final List<BatchesInventory> acquire(Goods goods) {
        return this.acquire(goods, null, ValidPeriodType.ALL);
    }

    public final List<BatchesInventory> acquire(Goods goods, Batches batches) {
        return this.acquire(goods, batches, ValidPeriodType.ALL);
    }

    public final List<BatchesInventory> acquire(Goods goods, Batches batches, ValidPeriodType validPeriodType) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().goods.eq(goods),
                batches == null ? this.getPath().batches.isNotNull() : this.getPath().batches.eq(batches),
                validPeriodType == ValidPeriodType.ALL ? this.getPath().type.isNotNull() : this.getPath().type.eq(validPeriodType)
            )
            .orderBy(
                this.getPath().type.asc(),
                this.getPath().batches.productionDate.asc(),
                this.getPath().batches.id.asc()
            )
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }
}