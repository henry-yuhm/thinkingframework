package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.InboundDetail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInboundDetail is a Querydsl query type for InboundDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInboundDetail extends EntityPathBase<InboundDetail> {

    public static final QInboundDetail inboundDetail = new QInboundDetail("inboundDetail");

    private static final long serialVersionUID = -1604497793L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QDetail _super;

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.Assessment> assessment = createEnum("assessment", org.thinking.logistics.services.core.domain.support.Assessment.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.Conveyance> conveyance = createEnum("conveyance", org.thinking.logistics.services.core.domain.support.Conveyance.class);

    public final NumberPath<java.math.BigDecimal> factPieces = createNumber("factPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factQuantity = createNumber("factQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factRemainder = createNumber("factRemainder", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    //inherited
    public final NumberPath<Long> id;

    public final QPurchaseOrderDetail order;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet pallet;

    public final QInboundDetail parent;

    public final NumberPath<java.math.BigDecimal> planPieces = createNumber("planPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planQuantity = createNumber("planQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planRemainder = createNumber("planRemainder", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.ReceiptConclusion> receiptConclusion = createEnum("receiptConclusion", org.thinking.logistics.services.core.domain.support.ReceiptConclusion.class);

    public final StringPath rejections = createString("rejections");

    public final StringPath scanningNo = createString("scanningNo");

    public final StringPath splitter = createString("splitter");

    public final StringPath storeNo = createString("storeNo");

    public final NumberPath<Integer> temperature = createNumber("temperature", Integer.class);

    public final org.thinking.logistics.services.core.domain.container.dsl.QTotebox totebox;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QInboundDetail(String variable) {
        this(InboundDetail.class, forVariable(variable), INITS);
    }

    public QInboundDetail(Path<? extends InboundDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInboundDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInboundDetail(PathMetadata metadata, PathInits inits) {
        this(InboundDetail.class, metadata, inits);
    }

    public QInboundDetail(Class<? extends InboundDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QDetail(type, metadata, inits);
        this.batches = _super.batches;
        this.goods = _super.goods;
        this.id = _super.id;
        this.order = inits.isInitialized("order") ? new QPurchaseOrderDetail(forProperty("order"), inits.get("order")) : null;
        this.pallet = inits.isInitialized("pallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("pallet"), inits.get("pallet")) : null;
        this.parent = inits.isInitialized("parent") ? new QInboundDetail(forProperty("parent"), inits.get("parent")) : null;
        this.totebox = inits.isInitialized("totebox") ? new org.thinking.logistics.services.core.domain.container.dsl.QTotebox(forProperty("totebox"), inits.get("totebox")) : null;
        this.warehouse = _super.warehouse;
    }

}

