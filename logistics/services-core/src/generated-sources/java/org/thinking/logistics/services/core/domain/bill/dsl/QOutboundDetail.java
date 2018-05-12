package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.OutboundDetail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QOutboundDetail is a Querydsl query type for OutboundDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOutboundDetail extends EntityPathBase<OutboundDetail> {

    public static final QOutboundDetail outboundDetail = new QOutboundDetail("outboundDetail");

    private static final long serialVersionUID = -40650580L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QDetail _super;

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final NumberPath<java.math.BigDecimal> factPieces = createNumber("factPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factQuantity = createNumber("factQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factRemainder = createNumber("factRemainder", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> flittingCostPrice = createNumber("flittingCostPrice", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> inventoryState = createEnum("inventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final NumberPath<java.math.BigDecimal> lessnessQuantity = createNumber("lessnessQuantity", java.math.BigDecimal.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation location;

    public final BooleanPath original = createBoolean("original");

    public final QOutboundDetail parent;

    public final NumberPath<java.math.BigDecimal> planPieces = createNumber("planPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planQuantity = createNumber("planQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planRemainder = createNumber("planRemainder", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> remainderQuantity = createNumber("remainderQuantity", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BatchesRequest> request = createEnum("request", org.thinking.logistics.services.core.domain.support.BatchesRequest.class);

    public final NumberPath<java.math.BigDecimal> settlementAmount = createNumber("settlementAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> settlementPrice = createNumber("settlementPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> taxes = createNumber("taxes", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public final NumberPath<java.math.BigDecimal> wholepiecesQuantity = createNumber("wholepiecesQuantity", java.math.BigDecimal.class);

    public QOutboundDetail(String variable) {
        this(OutboundDetail.class, forVariable(variable), INITS);
    }

    public QOutboundDetail(Path<? extends OutboundDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOutboundDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOutboundDetail(PathMetadata metadata, PathInits inits) {
        this(OutboundDetail.class, metadata, inits);
    }

    public QOutboundDetail(Class<? extends OutboundDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QDetail(type, metadata, inits);
        this.batches = _super.batches;
        this.goods = _super.goods;
        this.id = _super.id;
        this.location = inits.isInitialized("location") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("location"), inits.get("location")) : null;
        this.parent = inits.isInitialized("parent") ? new QOutboundDetail(forProperty("parent"), inits.get("parent")) : null;
        this.warehouse = _super.warehouse;
    }

}

