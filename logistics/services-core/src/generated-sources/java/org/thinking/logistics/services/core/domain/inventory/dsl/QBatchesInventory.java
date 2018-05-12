package org.thinking.logistics.services.core.domain.inventory.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.inventory.BatchesInventory;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QBatchesInventory is a Querydsl query type for BatchesInventory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBatchesInventory extends EntityPathBase<BatchesInventory> {

    public static final QBatchesInventory batchesInventory = new QBatchesInventory("batchesInventory");

    private static final long serialVersionUID = 1004789346L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final NumberPath<java.math.BigDecimal> availableInventory = createNumber("availableInventory", java.math.BigDecimal.class);

    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> intransitInventory = createNumber("intransitInventory", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.ValidPeriodType> mixedType = createEnum("mixedType", org.thinking.logistics.services.core.domain.support.ValidPeriodType.class);

    public final NumberPath<java.math.BigDecimal> palletInventory = createNumber("palletInventory", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> remainderInventory = createNumber("remainderInventory", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.ValidPeriodType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.ValidPeriodType.class);

    public final NumberPath<java.math.BigDecimal> wholepiecesInventory = createNumber("wholepiecesInventory", java.math.BigDecimal.class);

    public QBatchesInventory(String variable) {
        this(BatchesInventory.class, forVariable(variable), INITS);
    }

    public QBatchesInventory(Path<? extends BatchesInventory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBatchesInventory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBatchesInventory(PathMetadata metadata, PathInits inits) {
        this(BatchesInventory.class, metadata, inits);
    }

    public QBatchesInventory(Class<? extends BatchesInventory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.batches = inits.isInitialized("batches") ? new org.thinking.logistics.services.core.domain.dsl.QBatches(forProperty("batches"), inits.get("batches")) : null;
        this.goods = inits.isInitialized("goods") ? new org.thinking.logistics.services.core.domain.dsl.QGoods(forProperty("goods"), inits.get("goods")) : null;
    }

}

