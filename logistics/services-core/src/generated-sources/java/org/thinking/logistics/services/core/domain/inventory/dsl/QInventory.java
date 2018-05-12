package org.thinking.logistics.services.core.domain.inventory.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.inventory.Inventory;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInventory is a Querydsl query type for Inventory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInventory extends EntityPathBase<Inventory> {

    public static final QInventory inventory = new QInventory("inventory");

    private static final long serialVersionUID = -1269756018L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> inboundQuantity = createNumber("inboundQuantity", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> inventoryState = createEnum("inventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation location;

    public final BooleanPath locking = createBoolean("locking");

    public final NumberPath<java.math.BigDecimal> lockingQuantity = createNumber("lockingQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> minusQuantity = createNumber("minusQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> outboundQuantity = createNumber("outboundQuantity", java.math.BigDecimal.class);

    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet pallet;

    public final NumberPath<java.math.BigDecimal> pieces = createNumber("pieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> plusQuantity = createNumber("plusQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quantity = createNumber("quantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> remainder = createNumber("remainder", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> transitionalQuantity = createNumber("transitionalQuantity", java.math.BigDecimal.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QInventory(String variable) {
        this(Inventory.class, forVariable(variable), INITS);
    }

    public QInventory(Path<? extends Inventory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInventory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInventory(PathMetadata metadata, PathInits inits) {
        this(Inventory.class, metadata, inits);
    }

    public QInventory(Class<? extends Inventory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.batches = inits.isInitialized("batches") ? new org.thinking.logistics.services.core.domain.dsl.QBatches(forProperty("batches"), inits.get("batches")) : null;
        this.goods = inits.isInitialized("goods") ? new org.thinking.logistics.services.core.domain.dsl.QGoods(forProperty("goods"), inits.get("goods")) : null;
        this.location = inits.isInitialized("location") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("location"), inits.get("location")) : null;
        this.owner = inits.isInitialized("owner") ? new org.thinking.logistics.services.core.domain.dsl.QOwner(forProperty("owner")) : null;
        this.pallet = inits.isInitialized("pallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("pallet"), inits.get("pallet")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

