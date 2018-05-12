package org.thinking.logistics.services.core.domain.scheduler.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.scheduler.OrderScheduler;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QOrderScheduler is a Querydsl query type for OrderScheduler
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderScheduler extends EntityPathBase<OrderScheduler> {

    public static final QOrderScheduler orderScheduler = new QOrderScheduler("orderScheduler");

    private static final long serialVersionUID = -2129487652L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QScheduler _super;

    //inherited
    public final BooleanPath complete;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QOutboundHeader header;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.SchedulerType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QOrderScheduler(String variable) {
        this(OrderScheduler.class, forVariable(variable), INITS);
    }

    public QOrderScheduler(Path<? extends OrderScheduler> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderScheduler(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderScheduler(PathMetadata metadata, PathInits inits) {
        this(OrderScheduler.class, metadata, inits);
    }

    public QOrderScheduler(Class<? extends OrderScheduler> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QScheduler(type, metadata, inits);
        this.complete = _super.complete;
        this.creationTime = _super.creationTime;
        this.header = inits.isInitialized("header") ? new org.thinking.logistics.services.core.domain.bill.dsl.QOutboundHeader(forProperty("header"), inits.get("header")) : null;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

