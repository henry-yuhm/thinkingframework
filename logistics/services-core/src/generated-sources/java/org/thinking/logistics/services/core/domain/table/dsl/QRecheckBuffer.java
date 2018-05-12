package org.thinking.logistics.services.core.domain.table.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.table.RecheckBuffer;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QRecheckBuffer is a Querydsl query type for RecheckBuffer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRecheckBuffer extends EntityPathBase<RecheckBuffer> {

    public static final QRecheckBuffer recheckBuffer = new QRecheckBuffer("recheckBuffer");

    private static final long serialVersionUID = -448596615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath available = createBoolean("available");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath no = createString("no");

    public final QRecheckSlide slide;

    public final QRecheckTable table;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.RecheckBufferType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.RecheckBufferType.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QRecheckBuffer(String variable) {
        this(RecheckBuffer.class, forVariable(variable), INITS);
    }

    public QRecheckBuffer(Path<? extends RecheckBuffer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecheckBuffer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecheckBuffer(PathMetadata metadata, PathInits inits) {
        this(RecheckBuffer.class, metadata, inits);
    }

    public QRecheckBuffer(Class<? extends RecheckBuffer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.slide = inits.isInitialized("slide") ? new QRecheckSlide(forProperty("slide"), inits.get("slide")) : null;
        this.table = inits.isInitialized("table") ? new QRecheckTable(forProperty("table"), inits.get("table")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

