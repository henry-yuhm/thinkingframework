package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.InverseDetail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInverseDetail is a Querydsl query type for InverseDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInverseDetail extends EntityPathBase<InverseDetail> {

    public static final QInverseDetail inverseDetail = new QInverseDetail("inverseDetail");

    private static final long serialVersionUID = 920365302L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QDetail _super;

    public final StringPath auditor = createString("auditor");

    public final DatePath<java.sql.Date> auditTime = createDate("auditTime", java.sql.Date.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final QOutboundDetail detail;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final QOutboundHeader header;

    //inherited
    public final NumberPath<Long> id;

    public final DatePath<java.sql.Date> operationTime = createDate("operationTime", java.sql.Date.class);

    public final StringPath operator = createString("operator");

    public final NumberPath<java.math.BigDecimal> pieces = createNumber("pieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quantity = createNumber("quantity", java.math.BigDecimal.class);

    public final StringPath reasons = createString("reasons");

    public final NumberPath<java.math.BigDecimal> remainder = createNumber("remainder", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InverseStage> stage = createEnum("stage", org.thinking.logistics.services.core.domain.support.InverseStage.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QInverseDetail(String variable) {
        this(InverseDetail.class, forVariable(variable), INITS);
    }

    public QInverseDetail(Path<? extends InverseDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInverseDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInverseDetail(PathMetadata metadata, PathInits inits) {
        this(InverseDetail.class, metadata, inits);
    }

    public QInverseDetail(Class<? extends InverseDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QDetail(type, metadata, inits);
        this.batches = _super.batches;
        this.detail = inits.isInitialized("detail") ? new QOutboundDetail(forProperty("detail"), inits.get("detail")) : null;
        this.goods = _super.goods;
        this.header = inits.isInitialized("header") ? new QOutboundHeader(forProperty("header"), inits.get("header")) : null;
        this.id = _super.id;
        this.warehouse = _super.warehouse;
    }

}

