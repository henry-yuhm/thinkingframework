package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.ReplenishingDetail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QReplenishingDetail is a Querydsl query type for ReplenishingDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReplenishingDetail extends EntityPathBase<ReplenishingDetail> {

    public static final QReplenishingDetail replenishingDetail = new QReplenishingDetail("replenishingDetail");

    private static final long serialVersionUID = -625646536L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QDetail _super;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final NumberPath<java.math.BigDecimal> factPieces = createNumber("factPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factQuantity = createNumber("factQuantity", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    //inherited
    public final NumberPath<Long> id;

    public final org.thinking.logistics.services.core.domain.dsl.QLocation location;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet pallet;

    public final NumberPath<java.math.BigDecimal> planPieces = createNumber("planPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planQuantity = createNumber("planQuantity", java.math.BigDecimal.class);

    public final StringPath storeCategory = createString("storeCategory");

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QReplenishingDetail(String variable) {
        this(ReplenishingDetail.class, forVariable(variable), INITS);
    }

    public QReplenishingDetail(Path<? extends ReplenishingDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplenishingDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplenishingDetail(PathMetadata metadata, PathInits inits) {
        this(ReplenishingDetail.class, metadata, inits);
    }

    public QReplenishingDetail(Class<? extends ReplenishingDetail> type, PathMetadata metadata, PathInits inits) {
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

