package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.TransferringDetail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTransferringDetail is a Querydsl query type for TransferringDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransferringDetail extends EntityPathBase<TransferringDetail> {

    public static final QTransferringDetail transferringDetail = new QTransferringDetail("transferringDetail");

    private static final long serialVersionUID = -1243596649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QDetail _super;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    //inherited
    public final NumberPath<Long> id;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet pallet;

    public final NumberPath<java.math.BigDecimal> pieces = createNumber("pieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quantity = createNumber("quantity", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TransferringReason> reason = createEnum("reason", org.thinking.logistics.services.core.domain.support.TransferringReason.class);

    public final NumberPath<java.math.BigDecimal> remainder = createNumber("remainder", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> sourceInventoryState = createEnum("sourceInventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation sourceLocation;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> targetInventoryState = createEnum("targetInventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation targetLocation;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QTransferringDetail(String variable) {
        this(TransferringDetail.class, forVariable(variable), INITS);
    }

    public QTransferringDetail(Path<? extends TransferringDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransferringDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransferringDetail(PathMetadata metadata, PathInits inits) {
        this(TransferringDetail.class, metadata, inits);
    }

    public QTransferringDetail(Class<? extends TransferringDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QDetail(type, metadata, inits);
        this.batches = _super.batches;
        this.goods = _super.goods;
        this.id = _super.id;
        this.pallet = inits.isInitialized("pallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("pallet"), inits.get("pallet")) : null;
        this.sourceLocation = inits.isInitialized("sourceLocation") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("sourceLocation"), inits.get("sourceLocation")) : null;
        this.targetLocation = inits.isInitialized("targetLocation") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("targetLocation"), inits.get("targetLocation")) : null;
        this.warehouse = _super.warehouse;
    }

}

