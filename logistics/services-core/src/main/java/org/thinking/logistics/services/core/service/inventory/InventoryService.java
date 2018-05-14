package org.thinking.logistics.services.core.service.inventory;

import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.*;
import org.thinking.logistics.services.core.domain.command.QPilerCommand;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.domain.inventory.QInventory;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryService extends DomainService<QInventory, Inventory, Long> {
    @Autowired
    public InventoryService(EntityManager entityManager, DomainRepository<Inventory, Long> repository) {
        super(entityManager, repository, QInventory.inventory);
    }

    public final Inventory acquire(Warehouse warehouse, Owner owner, Goods goods, Batches batches, Location location, InventoryState inventoryState) {
        return this.getQueryFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().owner.eq(owner),
                this.getPath().goods.eq(goods),
                this.getPath().batches.eq(batches),
                this.getPath().location.eq(location),
                this.getPath().inventoryState.eq(inventoryState))
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetchOne();
    }

    public final Object acquire(Warehouse warehouse, Goods goods, Batches batches, int batchesNumber, ValidPeriodType validPeriodType) {
        EnumExpression<ValidPeriodType> type = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(this.getPath().location.type.eq(LocationType.NORMAL)))
            .then(ValidPeriodType.ALL)
            .otherwise(ValidPeriodType.OLD);

        NumberExpression<BigDecimal> availableInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.REMAINDER),
                    this.getPath().goods.splittingGranularity.eq(SplittingGranularity.MEDIUM_PACKAGE)))
            .then(this.getPath().availableOutboundQuantity.divide(this.getPath().goods.mediumPackageQuantity).floor().multiply(this.getPath().goods.mediumPackageQuantity))
            .otherwise(this.getPath().availableOutboundQuantity);

        NumberExpression<BigDecimal> palletInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.WHOLEPIECES),
                    this.getPath().location.automatic.isTrue()))
            .then(this.getPath().availableOutboundQuantity)
            .otherwise(BigDecimal.ZERO);

        NumberExpression<BigDecimal> wholepiecesInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.WHOLEPIECES),
                    this.getPath().location.automatic.isFalse()))
            .then(this.getPath().availableOutboundQuantity)
            .otherwise(BigDecimal.ZERO);

        NumberExpression<BigDecimal> remainderInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.REMAINDER),
                    this.getPath().goods.splittingGranularity.eq(SplittingGranularity.MEDIUM_PACKAGE)))
            .then(this.getPath().availableOutboundQuantity.divide(this.getPath().goods.mediumPackageQuantity).floor().multiply(this.getPath().goods.mediumPackageQuantity))
            .otherwise(this.getPath().availableOutboundQuantity);

        NumberExpression<BigDecimal> intransitInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.WHOLEPIECES),
                    this.getPath().location.automatic.isTrue()))
            .then(this.getPath().transitionalQuantity)
            .otherwise(BigDecimal.ZERO);

        return this.getQueryFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().goods.eq(goods),
                batchesNumber == 0 ? this.getPath().batches.eq(batches) : this.getPath().batches.isNotNull()
            )
            .select(
                this.getPath().goods,
                this.getPath().batches,
                type,
                availableInventory.sum(),
                palletInventory.sum(),
                wholepiecesInventory.sum(),
                remainderInventory.sum(),
                intransitInventory.sum()
            )
            .groupBy(
                this.getPath().goods,
                this.getPath().batches,
                type
            )
            .having(
                availableInventory.sum().gt(0),
                validPeriodType.compareTo(ValidPeriodType.ALL) == 0 ? type.isNotNull() : type.eq(validPeriodType)
            )
            .orderBy(
                type.asc(),
                this.getPath().batches.productionDate.asc(),
                this.getPath().batches.id.asc()
            )
            .fetch()
            .stream()
            .map(tuple -> {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("goods", tuple.get(this.getPath().goods));
                map.put("batches", tuple.get(this.getPath().batches));
                map.put("type", tuple.get(type));
                map.put("availableInventory", tuple.get(availableInventory.sum()));
                map.put("palletInventory", tuple.get(palletInventory.sum()));
                map.put("wholepiecesInventory", tuple.get(wholepiecesInventory.sum()));
                map.put("remainderInventory", tuple.get(remainderInventory.sum()));
                map.put("intransitInventory", tuple.get(intransitInventory.sum()));
                return map;
            })
            .collect(Collectors.toList());
    }

    public final List<Inventory> acquire(Warehouse warehouse, Goods goods, Batches batches) {
        return this.getQueryFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().goods.eq(goods),
                this.getPath().batches.eq(batches),
                this.getPath().transitionalQuantity.gt(0))
            .orderBy(this.getPath().transitionalQuantity.asc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }

    public final List<Inventory> acquire(Warehouse warehouse, Goods goods, Batches batches, InventoryState inventoryState, String storeCategory, String storeNo, BigDecimal quantity) {
        return this.getQueryFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().goods.eq(goods),
                this.getPath().batches.eq(batches),
                this.getPath().inventoryState.eq(inventoryState),
                this.getPath().location.area.storeCategory.eq(storeCategory),
                this.getPath().location.area.storeNo.eq(storeNo),
                this.getPath().transitionalQuantity.eq(BigDecimal.ZERO))
            .orderBy(
                this.getPath().location.area.storeCategory.when("LTK")
                    .then(
                        JPAExpressions.selectFrom(QPilerCommand.pilerCommand)
//                        .where(QPilerCommand.pilerCommand)
                            .fetchCount())
                    .otherwise(1L)
                    .asc(),
                this.getPath().availableOutboundQuantity.subtract(quantity).asc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }
}