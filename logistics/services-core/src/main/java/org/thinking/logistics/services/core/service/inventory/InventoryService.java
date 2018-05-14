package org.thinking.logistics.services.core.service.inventory;

import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.services.core.domain.Batches;
import org.thinking.logistics.services.core.domain.Goods;
import org.thinking.logistics.services.core.domain.Location;
import org.thinking.logistics.services.core.domain.Warehouse;
import org.thinking.logistics.services.core.domain.command.QPilerCommand;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.domain.inventory.QInventory;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.ContainerService;
import org.thinking.logistics.services.core.service.DomainService;
import org.thinking.logistics.services.core.service.LocationService;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryService extends DomainService<QInventory, Inventory, Long> {
    private LocationService locationService;

    private ContainerService containerService;

    @Autowired
    public InventoryService(EntityManager entityManager, DomainRepository<Inventory, Long> repository, LocationService locationService, ContainerService containerService) {
        super(entityManager, repository, QInventory.inventory);
        this.locationService = locationService;
        this.containerService = containerService;
    }

    public final Inventory acquire(Warehouse warehouse, Goods goods, Batches batches, Location location, InventoryState inventoryState) {
        return this.getQueryFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().goods.eq(goods),
                this.getPath().batches.eq(batches),
                this.getPath().location.eq(location),
                this.getPath().inventoryState.eq(inventoryState)
            )
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

    public final BigDecimal acquire(Warehouse warehouse, Goods goods) {
        return this.getQueryFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().goods.eq(goods)
            )
            .select(this.getPath().quantity.sum())
            .fetchOne();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Inventory inventory, LedgerType type, LedgerCategory category, BigDecimal quantity) throws Exception {
        //region 设置库存属性
        if (type == LedgerType.PREALLOCATION) {
            switch (category) {
                case INBOUND:
                    inventory.setInboundQuantity(inventory.getInboundQuantity().add(quantity));
                    break;
                case OUTBOUND:
                    inventory.setOutboundQuantity(inventory.getOutboundQuantity().add(quantity));
                    break;
                case REPLENISHED_FROM:
                    inventory.setReplenishedFromQuantity(inventory.getReplenishedFromQuantity().add(quantity));
                    break;
                case REPLENISHED_TO:
                    inventory.setReplenishedToQuantity(inventory.getReplenishedToQuantity().add(quantity));
                    break;
                case TRANSFERRED_FROM:
                    inventory.setTransferredFromQuantity(inventory.getTransferredFromQuantity().add(quantity));
                    break;
                case TRANSFERRED_TO:
                    inventory.setTransferredToQuantity(inventory.getTransferredToQuantity().add(quantity));
                    break;
            }
        }

        if (type == LedgerType.CHARGING) {
            inventory.setQuantity(inventory.getQuantity().add(quantity));
            inventory.setPieces(inventory.getGoods().getPieces(inventory.getQuantity()));
            inventory.setRemainder(inventory.getGoods().getRemainder(inventory.getQuantity()));
        }

        if (type == LedgerType.IN_TRANSITION) {
            inventory.setTransitionalQuantity(inventory.getTransitionalQuantity().add(quantity));
        }

        if (type == LedgerType.LOCKING) {
            switch (category) {
                case LOCKING:
                    inventory.setLocking(true);
                    inventory.setLockingQuantity(inventory.getQuantity().subtract(inventory.getOutboundQuantity()).subtract(inventory.getReplenishedFromQuantity()).add(inventory.getReplenishedToQuantity()).subtract(inventory.getTransferredFromQuantity()).add(inventory.getTransferredToQuantity()));
                    break;
                case UNLOCKING:
                    inventory.setLocking(false);
                    inventory.setLockingQuantity(BigDecimal.ZERO);
                    break;
            }
        }
        //endregion

        //region 计算当前货位上的占用体积
        BigDecimal occupationVolume = this.getQueryFactory().selectFrom(this.getPath())
            .where(this.getPath().location.eq(inventory.getLocation()))
            .select(((this.getPath().quantity
                    .add(this.getPath().inboundQuantity)
                    .add(this.getPath().replenishedToQuantity)
                    .add(this.getPath().transferredToQuantity)
                )
                    .multiply(this.getPath().goods.smallPackageVolume)
                    .multiply(this.getPath().goods.volumeRatio)
                )
                    .sum()
            )
            .fetchOne();

        inventory.getLocation().setOccupationVolume(inventory.getLocation().getOccupationVolume().add(quantity.multiply(inventory.getGoods().getSmallPackageVolume()).multiply(inventory.getGoods().getVolumeRatio())));

        if (inventory.getLocation().getOccupationVolume().compareTo(BigDecimal.ZERO) < 0) {
            inventory.getLocation().setOccupationVolume(BigDecimal.ZERO);
        }

        if (inventory.getLocation().getRackType() == RackType.FLUENCY) {
            inventory.getLocation().setOccupationVolume(inventory.getLocation().getOccupationVolume().divide(inventory.getGoods().getSmallPackageVolume(), RoundingMode.CEILING).multiply(inventory.getGoods().getSmallPackageVolume()).multiply(inventory.getGoods().getVolumeRatio()));
        }

        this.locationService.getRepository().save(inventory.getLocation());
        //endregion

        //region 更新库存
        JPAUpdateClause update = this.getQueryFactory().update(this.getPath())
            .where(this.getPath().id.eq(inventory.getId()));

        if (type == LedgerType.PREALLOCATION) {
            switch (category) {
                case INBOUND:
                    update = update.set(this.getPath().inboundQuantity, inventory.getInboundQuantity());
                    break;
                case OUTBOUND:
                    update = update.set(this.getPath().outboundQuantity, inventory.getOutboundQuantity());
                    break;
                case REPLENISHED_FROM:
                    update = update.set(this.getPath().replenishedFromQuantity, inventory.getReplenishedFromQuantity());
                    break;
                case REPLENISHED_TO:
                    update = update.set(this.getPath().replenishedToQuantity, inventory.getReplenishedToQuantity());
                    break;
                case TRANSFERRED_FROM:
                    update = update.set(this.getPath().transferredFromQuantity, inventory.getTransferredFromQuantity());
                    break;
                case TRANSFERRED_TO:
                    update = update.set(this.getPath().transferredToQuantity, inventory.getTransferredToQuantity());
                    break;
            }
        }

        if (type == LedgerType.CHARGING) {
            update = update
                .set(this.getPath().quantity, inventory.getQuantity())
                .set(this.getPath().pieces, inventory.getPieces())
                .set(this.getPath().remainder, inventory.getRemainder());
        }

        if (type == LedgerType.IN_TRANSITION) {
            update = update.set(this.getPath().transitionalQuantity, inventory.getTransitionalQuantity());
        }

        if (type == LedgerType.LOCKING) {
            update = update
                .set(this.getPath().locking, inventory.isLocking())
                .set(this.getPath().lockingQuantity, inventory.getLockingQuantity());
        }

        if (inventory.getPallet() != null) {
            update = update.set(this.getPath().pallet, inventory.getPallet());
        }

        if (update.execute() == 0) {
            this.getRepository().save(inventory);

            if (inventory.getPallet() != null) {
                inventory.getPallet().setAvailable(false);
                this.containerService.getRepository().save(inventory.getPallet());
            }
        }
        //endregion

        //region 无效库存清理
        if (this.getQueryFactory().delete(this.getPath())
            .where(
                this.getPath().warehouse.eq(inventory.getWarehouse()),
                this.getPath().owner.eq(inventory.getOwner()),
                this.getPath().goods.eq(inventory.getGoods()),
                this.getPath().batches.eq(inventory.getBatches()),
                this.getPath().location.eq(inventory.getLocation()),
                this.getPath().inventoryState.eq(inventory.getInventoryState()),
                this.getPath().quantity.eq(BigDecimal.ZERO),
                this.getPath().inboundQuantity.eq(BigDecimal.ZERO),
                this.getPath().outboundQuantity.eq(BigDecimal.ZERO),
                this.getPath().replenishedFromQuantity.eq(BigDecimal.ZERO),
                this.getPath().replenishedToQuantity.eq(BigDecimal.ZERO),
                this.getPath().transferredFromQuantity.eq(BigDecimal.ZERO),
                this.getPath().transferredToQuantity.eq(BigDecimal.ZERO),
                this.getPath().transitionalQuantity.eq(BigDecimal.ZERO),
                this.getPath().lockingQuantity.eq(BigDecimal.ZERO)
            )
            .execute() > 0) {
            if (inventory.getPallet() != null) {
                inventory.getPallet().setAvailable(true);
                this.containerService.getRepository().save(inventory.getPallet());
            }
        }
        //endregion
    }
}