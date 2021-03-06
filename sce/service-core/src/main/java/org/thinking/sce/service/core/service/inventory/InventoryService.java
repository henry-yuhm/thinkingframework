package org.thinking.sce.service.core.service.inventory;

import com.querydsl.core.types.dsl.EnumExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.sce.service.core.domain.command.QPilerCommand;
import org.thinking.sce.service.core.domain.common.Item;
import org.thinking.sce.service.core.domain.common.Location;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.inventory.Inventory;
import org.thinking.sce.service.core.domain.inventory.QInventory;
import org.thinking.sce.service.core.domain.support.InventoryState;
import org.thinking.sce.service.core.domain.support.LedgerCategory;
import org.thinking.sce.service.core.domain.support.LedgerType;
import org.thinking.sce.service.core.domain.support.LocationType;
import org.thinking.sce.service.core.domain.support.PackageType;
import org.thinking.sce.service.core.domain.support.RackType;
import org.thinking.sce.service.core.domain.support.SplittingGranularity;
import org.thinking.sce.service.core.domain.support.ValidPeriodType;
import org.thinking.sce.service.core.repository.DomainRepository;
import org.thinking.sce.service.core.service.DomainService;
import org.thinking.sce.service.core.service.LocationService;
import org.thinking.sce.service.core.service.container.PalletService;

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

    private PalletService palletService;

    @Autowired
    public InventoryService(EntityManager entityManager, DomainRepository<Inventory, Long> repository, LocationService locationService, PalletService palletService) {
        super(entityManager, repository, QInventory.inventory);
        this.locationService = locationService;
        this.palletService = palletService;
    }

    public final Inventory acquire(Warehouse warehouse, Item item, Lot lot, Location location, InventoryState inventoryState) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().item.eq(item),
                this.getPath().lot.eq(lot),
                this.getPath().location.eq(location),
                this.getPath().inventoryState.eq(inventoryState)
            )
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetchOne();
    }

    public final Object acquire(Warehouse warehouse, Item item, Lot lot, int lotNumbers, ValidPeriodType validPeriodType) {
        EnumExpression<ValidPeriodType> type = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(this.getPath().location.type.eq(LocationType.NORMAL)))
            .then(ValidPeriodType.ALL)
            .otherwise(ValidPeriodType.OLD);

        NumberExpression<BigDecimal> availableInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.REMAINDER),
                    this.getPath().item.splittingGranularity.eq(SplittingGranularity.MEDIUM_PACKAGE)))
            .then(this.getPath().availableOutboundQuantity.divide(this.getPath().item.mediumPackageQuantity).floor().multiply(this.getPath().item.mediumPackageQuantity))
            .otherwise(this.getPath().availableOutboundQuantity);

        NumberExpression<BigDecimal> palletInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.WHOLEPIECES),
                    this.getPath().location.automatic.isTrue()))
            .then(this.getPath().availableOutboundQuantity)
            .otherwise(BigDecimal.ZERO);

        NumberExpression<BigDecimal> casesInventory = this.getPath()
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
                    this.getPath().item.splittingGranularity.eq(SplittingGranularity.MEDIUM_PACKAGE)))
            .then(this.getPath().availableOutboundQuantity.divide(this.getPath().item.mediumPackageQuantity).floor().multiply(this.getPath().item.mediumPackageQuantity))
            .otherwise(this.getPath().availableOutboundQuantity);

        NumberExpression<BigDecimal> intransitInventory = this.getPath()
            .when(JPAExpressions.selectFrom(this.getPath())
                .where(
                    this.getPath().location.packageType.eq(PackageType.WHOLEPIECES),
                    this.getPath().location.automatic.isTrue()))
            .then(this.getPath().transitionalQuantity)
            .otherwise(BigDecimal.ZERO);

        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().item.eq(item),
                lotNumbers == 0 ? this.getPath().lot.eq(lot) : this.getPath().lot.isNotNull()
            )
            .select(
                this.getPath().item,
                this.getPath().lot,
                type,
                availableInventory.sum(),
                palletInventory.sum(),
                casesInventory.sum(),
                remainderInventory.sum(),
                intransitInventory.sum()
            )
            .groupBy(
                this.getPath().item,
                this.getPath().lot,
                type
            )
            .having(
                availableInventory.sum().gt(0),
                validPeriodType.compareTo(ValidPeriodType.ALL) == 0 ? type.isNotNull() : type.eq(validPeriodType)
            )
            .orderBy(
                type.asc(),
                this.getPath().lot.productionDate.asc(),
                this.getPath().lot.id.asc()
            )
            .fetch()
            .stream()
            .map(tuple -> {
                Map<String, Object> map = new LinkedHashMap<>(16);
                map.put("item", tuple.get(this.getPath().item));
                map.put("lot", tuple.get(this.getPath().lot));
                map.put("type", tuple.get(type));
                map.put("availableInventory", tuple.get(availableInventory.sum()));
                map.put("palletInventory", tuple.get(palletInventory.sum()));
                map.put("casesInventory", tuple.get(casesInventory.sum()));
                map.put("remainderInventory", tuple.get(remainderInventory.sum()));
                map.put("intransitInventory", tuple.get(intransitInventory.sum()));
                return map;
            })
            .collect(Collectors.toList());
    }

    public final List<Inventory> acquire(Warehouse warehouse, Item item, Lot lot) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().item.eq(item),
                this.getPath().lot.eq(lot),
                this.getPath().transitionalQuantity.gt(0))
            .orderBy(this.getPath().transitionalQuantity.asc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetch();
    }

    public final List<Inventory> acquire(Warehouse warehouse, Item item, Lot lot, InventoryState inventoryState, String storeCategory, String storeNo, BigDecimal quantity) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().item.eq(item),
                this.getPath().lot.eq(lot),
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

    public final BigDecimal acquire(Warehouse warehouse, Item item) {
        return this.getFactory().selectFrom(this.getPath())
            .where(
                this.getPath().warehouse.eq(warehouse),
                this.getPath().item.eq(item)
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
                case REPLENISHING_FROM:
                    inventory.setReplenishedFromQuantity(inventory.getReplenishedFromQuantity().add(quantity));
                    break;
                case REPLENISHING_TO:
                    inventory.setReplenishedToQuantity(inventory.getReplenishedToQuantity().add(quantity));
                    break;
                case MOVING_FROM:
                    inventory.setMovingFromQuantity(inventory.getMovingFromQuantity().add(quantity));
                    break;
                case MOVING_TO:
                    inventory.setMovingToQuantity(inventory.getMovingToQuantity().add(quantity));
                    break;
            }
        }

        if (type == LedgerType.CHARGING) {
            inventory.setQuantity(inventory.getQuantity().add(quantity));
        }

        if (type == LedgerType.TRANSITION) {
            inventory.setTransitionalQuantity(inventory.getTransitionalQuantity().add(quantity));
        }

        if (type == LedgerType.LOCKING) {
            switch (category) {
                case LOCKING:
                    inventory.setLocking(true);
                    inventory.setLockingQuantity(inventory.getQuantity().subtract(inventory.getOutboundQuantity()).subtract(inventory.getReplenishedFromQuantity()).add(inventory.getReplenishedToQuantity()).subtract(inventory.getMovingFromQuantity()).add(inventory.getMovingToQuantity()));
                    break;
                case UNLOCKING:
                    inventory.setLocking(false);
                    inventory.setLockingQuantity(BigDecimal.ZERO);
                    break;
            }
        }
        //endregion

        //region 计算当前货位上的占用体积
        BigDecimal occupationVolume = this.getFactory().selectFrom(this.getPath())
            .where(this.getPath().location.eq(inventory.getLocation()))
            .select(((this.getPath().quantity
                    .add(this.getPath().inboundQuantity)
                    .add(this.getPath().replenishedToQuantity)
                    .add(this.getPath().movingToQuantity)
                )
                    .multiply(this.getPath().item.smallPackageVolume)
                    .multiply(this.getPath().item.volumeRatio)
                )
                    .sum()
            )
            .fetchOne();

        inventory.getLocation().setOccupationVolume(inventory.getLocation().getOccupationVolume().add(quantity.multiply(inventory.getItem().getSmallPackageVolume()).multiply(inventory.getItem().getVolumeRatio())));

        if (inventory.getLocation().getOccupationVolume().compareTo(BigDecimal.ZERO) < 0) {
            inventory.getLocation().setOccupationVolume(BigDecimal.ZERO);
        }

        if (inventory.getLocation().getRackType() == RackType.FLUENCY) {
            inventory.getLocation().setOccupationVolume(inventory.getLocation().getOccupationVolume().divide(inventory.getItem().getSmallPackageVolume(), RoundingMode.CEILING).multiply(inventory.getItem().getSmallPackageVolume()).multiply(inventory.getItem().getVolumeRatio()));
        }

        this.locationService.getRepository().save(inventory.getLocation());
        //endregion

        //region 更新库存
        JPAUpdateClause update = this.getFactory().update(this.getPath())
            .where(this.getPath().id.eq(inventory.getId()));

        if (type == LedgerType.PREALLOCATION) {
            switch (category) {
                case INBOUND:
                    update = update.set(this.getPath().inboundQuantity, inventory.getInboundQuantity());
                    break;
                case OUTBOUND:
                    update = update.set(this.getPath().outboundQuantity, inventory.getOutboundQuantity());
                    break;
                case REPLENISHING_FROM:
                    update = update.set(this.getPath().replenishedFromQuantity, inventory.getReplenishedFromQuantity());
                    break;
                case REPLENISHING_TO:
                    update = update.set(this.getPath().replenishedToQuantity, inventory.getReplenishedToQuantity());
                    break;
                case MOVING_FROM:
                    update = update.set(this.getPath().movingFromQuantity, inventory.getMovingFromQuantity());
                    break;
                case MOVING_TO:
                    update = update.set(this.getPath().movingToQuantity, inventory.getMovingToQuantity());
                    break;
            }
        }

        if (type == LedgerType.CHARGING) {
            update = update
                .set(this.getPath().quantity, inventory.getQuantity())
                .set(this.getPath().cases, inventory.getCases())
                .set(this.getPath().remainder, inventory.getRemainder());
        }

        if (type == LedgerType.TRANSITION) {
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
                this.palletService.getRepository().save(inventory.getPallet());
            }
        }
        //endregion

        //region 无效库存清理
        if (this.getFactory().delete(this.getPath())
            .where(
                this.getPath().warehouse.eq(inventory.getWarehouse()),
                this.getPath().owner.eq(inventory.getOwner()),
                this.getPath().item.eq(inventory.getItem()),
                this.getPath().lot.eq(inventory.getLot()),
                this.getPath().location.eq(inventory.getLocation()),
                this.getPath().inventoryState.eq(inventory.getInventoryState()),
                this.getPath().quantity.eq(BigDecimal.ZERO),
                this.getPath().inboundQuantity.eq(BigDecimal.ZERO),
                this.getPath().outboundQuantity.eq(BigDecimal.ZERO),
                this.getPath().replenishedFromQuantity.eq(BigDecimal.ZERO),
                this.getPath().replenishedToQuantity.eq(BigDecimal.ZERO),
                this.getPath().movingFromQuantity.eq(BigDecimal.ZERO),
                this.getPath().movingToQuantity.eq(BigDecimal.ZERO),
                this.getPath().transitionalQuantity.eq(BigDecimal.ZERO),
                this.getPath().lockingQuantity.eq(BigDecimal.ZERO)
            )
            .execute() > 0) {
            if (inventory.getPallet() != null) {
                inventory.getPallet().setAvailable(true);
                this.palletService.getRepository().save(inventory.getPallet());
            }
        }
        //endregion
    }
}