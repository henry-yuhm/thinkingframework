package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.Detail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QDetail is a Querydsl query type for Detail
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QDetail extends EntityPathBase<Detail> {

    public static final QDetail detail = new QDetail("detail");

    private static final long serialVersionUID = -67940196L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QDetail(String variable) {
        this(Detail.class, forVariable(variable), INITS);
    }

    public QDetail(Path<? extends Detail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDetail(PathMetadata metadata, PathInits inits) {
        this(Detail.class, metadata, inits);
    }

    public QDetail(Class<? extends Detail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.batches = inits.isInitialized("batches") ? new org.thinking.logistics.services.core.domain.dsl.QBatches(forProperty("batches"), inits.get("batches")) : null;
        this.goods = inits.isInitialized("goods") ? new org.thinking.logistics.services.core.domain.dsl.QGoods(forProperty("goods"), inits.get("goods")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

