package org.thinking.logistics.services.core.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.core.Batch;
import org.thinking.logistics.services.core.domain.core.Goods;
import org.thinking.logistics.services.core.domain.inventory.BatchInventory;
import org.thinking.logistics.services.core.domain.inventory.QBatchInventory;
import org.thinking.logistics.services.core.domain.support.ValidPeriodType;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@Service
public class BatchInventoryService extends DomainService<QBatchInventory, BatchInventory, Long> {
    @Autowired
    public BatchInventoryService(EntityManager entityManager, DomainRepository<BatchInventory, Long> repository) {
        super(entityManager, repository, QBatchInventory.batchInventory);
    }

    public final List<BatchInventory> acquire(Goods goods) {
        return this.acquire(goods, null, ValidPeriodType.ALL);
    }

    public final List<BatchInventory> acquire(Goods goods, Batch batch) {
        return this.acquire(goods, batch, ValidPeriodType.ALL);
    }

    public final List<BatchInventory> acquire(Goods goods, Batch batch, ValidPeriodType validPeriodType) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().goods.eq(goods),
                batch == null ? this.getPath().batch.isNotNull() : this.getPath().batch.eq(batch),
                validPeriodType == ValidPeriodType.ALL ? this.getPath().type.isNotNull() : this.getPath().type.eq(validPeriodType)
            )
            .orderBy(
                this.getPath().type.asc(),
                this.getPath().batch.productionDate.asc(),
                this.getPath().batch.id.asc()
            )
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }
}