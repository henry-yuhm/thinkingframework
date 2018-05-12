package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Task;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTask extends EntityPathBase<Task> {

    public static final QTask task = new QTask("task");

    private static final long serialVersionUID = 877232577L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final NumberPath<Integer> batchNo = createNumber("batchNo", Integer.class);

    public final org.thinking.logistics.services.core.domain.table.dsl.QRecheckBuffer buffer;

    public final StringPath bufferNo = createString("bufferNo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath no = createString("no");

    public final StringPath picker = createString("picker");

    public final DatePath<java.sql.Date> pickingCompleteTime = createDate("pickingCompleteTime", java.sql.Date.class);

    public final DatePath<java.sql.Date> pickingStartTime = createDate("pickingStartTime", java.sql.Date.class);

    public final DatePath<java.sql.Date> recheckCompleteTime = createDate("recheckCompleteTime", java.sql.Date.class);

    public final StringPath rechecker = createString("rechecker");

    public final DatePath<java.sql.Date> recheckStartTime = createDate("recheckStartTime", java.sql.Date.class);

    public final StringPath splittingBill = createString("splittingBill");

    public final org.thinking.logistics.services.core.domain.container.dsl.QTotebox totebox;

    public final QWarehouse warehouse;

    public QTask(String variable) {
        this(Task.class, forVariable(variable), INITS);
    }

    public QTask(Path<? extends Task> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTask(PathMetadata metadata, PathInits inits) {
        this(Task.class, metadata, inits);
    }

    public QTask(Class<? extends Task> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buffer = inits.isInitialized("buffer") ? new org.thinking.logistics.services.core.domain.table.dsl.QRecheckBuffer(forProperty("buffer"), inits.get("buffer")) : null;
        this.totebox = inits.isInitialized("totebox") ? new org.thinking.logistics.services.core.domain.container.dsl.QTotebox(forProperty("totebox"), inits.get("totebox")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

