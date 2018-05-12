package org.thinking.logistics.services.core.domain.scheduler.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.scheduler.Scheduler;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QScheduler is a Querydsl query type for Scheduler
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QScheduler extends EntityPathBase<Scheduler> {

    public static final QScheduler scheduler = new QScheduler("scheduler");

    private static final long serialVersionUID = 1841427340L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath complete = createBoolean("complete");

    public final DatePath<java.sql.Date> creationTime = createDate("creationTime", java.sql.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.sql.Date> modificationTime = createDate("modificationTime", java.sql.Date.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.SchedulerType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.SchedulerType.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QScheduler(String variable) {
        this(Scheduler.class, forVariable(variable), INITS);
    }

    public QScheduler(Path<? extends Scheduler> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScheduler(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScheduler(PathMetadata metadata, PathInits inits) {
        this(Scheduler.class, metadata, inits);
    }

    public QScheduler(Class<? extends Scheduler> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

