package org.thinking.logistics.services.core.domain.scheduler.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.scheduler.InventoryScheduler;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInventoryScheduler is a Querydsl query type for InventoryScheduler
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInventoryScheduler extends EntityPathBase<InventoryScheduler> {

    public static final QInventoryScheduler inventoryScheduler = new QInventoryScheduler("inventoryScheduler");

    private static final long serialVersionUID = -1875511538L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QScheduler _super;

    public final SetPath<org.thinking.logistics.services.core.domain.command.OutboundCommand, org.thinking.logistics.services.core.domain.command.dsl.QOutboundCommand> commands = this.<org.thinking.logistics.services.core.domain.command.OutboundCommand, org.thinking.logistics.services.core.domain.command.dsl.QOutboundCommand>createSet("commands", org.thinking.logistics.services.core.domain.command.OutboundCommand.class, org.thinking.logistics.services.core.domain.command.dsl.QOutboundCommand.class, PathInits.DIRECT2);

    //inherited
    public final BooleanPath complete;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.SchedulerType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QInventoryScheduler(String variable) {
        this(InventoryScheduler.class, forVariable(variable), INITS);
    }

    public QInventoryScheduler(Path<? extends InventoryScheduler> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInventoryScheduler(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInventoryScheduler(PathMetadata metadata, PathInits inits) {
        this(InventoryScheduler.class, metadata, inits);
    }

    public QInventoryScheduler(Class<? extends InventoryScheduler> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QScheduler(type, metadata, inits);
        this.complete = _super.complete;
        this.creationTime = _super.creationTime;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

