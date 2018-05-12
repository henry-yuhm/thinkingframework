package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Batches;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QBatches is a Querydsl query type for Batches
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBatches extends EntityPathBase<Batches> {

    public static final QBatches batches = new QBatches("batches");

    private static final long serialVersionUID = -34703444L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final StringPath approvalNo = createString("approvalNo");

    public final QGoods goods;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath no = createString("no");

    public final QOwner owner;

    public final StringPath printProductionDate = createString("printProductionDate");

    public final StringPath printValidUntil = createString("printValidUntil");

    public final DateTimePath<java.util.Date> productionDate = createDateTime("productionDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> validUntil = createDateTime("validUntil", java.util.Date.class);

    public QBatches(String variable) {
        this(Batches.class, forVariable(variable), INITS);
    }

    public QBatches(Path<? extends Batches> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBatches(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBatches(PathMetadata metadata, PathInits inits) {
        this(Batches.class, metadata, inits);
    }

    public QBatches(Class<? extends Batches> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goods = inits.isInitialized("goods") ? new QGoods(forProperty("goods"), inits.get("goods")) : null;
        this.owner = inits.isInitialized("owner") ? new QOwner(forProperty("owner")) : null;
    }

}

