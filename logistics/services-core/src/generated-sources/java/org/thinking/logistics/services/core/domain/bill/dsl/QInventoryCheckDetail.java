package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.InventoryCheckDetail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInventoryCheckDetail is a Querydsl query type for InventoryCheckDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInventoryCheckDetail extends EntityPathBase<InventoryCheckDetail> {

    public static final QInventoryCheckDetail inventoryCheckDetail = new QInventoryCheckDetail("inventoryCheckDetail");

    private static final long serialVersionUID = -326387320L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QDetail _super;

    public final StringPath auditor = createString("auditor");

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> inventoryState = createEnum("inventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation location;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet pallet;

    public final NumberPath<java.math.BigDecimal> pieces = createNumber("pieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quantity = createNumber("quantity", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TransferringReason> reason = createEnum("reason", org.thinking.logistics.services.core.domain.support.TransferringReason.class);

    public final NumberPath<java.math.BigDecimal> remainder = createNumber("remainder", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QInventoryCheckDetail(String variable) {
        this(InventoryCheckDetail.class, forVariable(variable), INITS);
    }

    public QInventoryCheckDetail(Path<? extends InventoryCheckDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInventoryCheckDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInventoryCheckDetail(PathMetadata metadata, PathInits inits) {
        this(InventoryCheckDetail.class, metadata, inits);
    }

    public QInventoryCheckDetail(Class<? extends InventoryCheckDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QDetail(type, metadata, inits);
        this.batches = _super.batches;
        this.goods = _super.goods;
        this.id = _super.id;
        this.location = inits.isInitialized("location") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("location"), inits.get("location")) : null;
        this.pallet = inits.isInitialized("pallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("pallet"), inits.get("pallet")) : null;
        this.warehouse = _super.warehouse;
    }

}

