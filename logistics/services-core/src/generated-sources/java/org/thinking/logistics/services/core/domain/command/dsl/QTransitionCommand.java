package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.TransitionCommand;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTransitionCommand is a Querydsl query type for TransitionCommand
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QTransitionCommand extends EntityPathBase<TransitionCommand> {

    public static final QTransitionCommand transitionCommand = new QTransitionCommand("transitionCommand");

    private static final long serialVersionUID = -1591114345L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QCommand _super;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory> commandCategory;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandType> commandType;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType;

    public final BooleanPath sourceActivated = createBoolean("sourceActivated");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> sourceInventoryState = createEnum("sourceInventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation sourceLocation;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandStage> stage;

    public final BooleanPath targetActivated = createBoolean("targetActivated");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> targetInventoryState = createEnum("targetInventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation targetLocation;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.WorkMode> workMode;

    public QTransitionCommand(String variable) {
        this(TransitionCommand.class, forVariable(variable), INITS);
    }

    public QTransitionCommand(Path<? extends TransitionCommand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransitionCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransitionCommand(PathMetadata metadata, PathInits inits) {
        this(TransitionCommand.class, metadata, inits);
    }

    public QTransitionCommand(Class<? extends TransitionCommand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCommand(type, metadata, inits);
        this.commandCategory = _super.commandCategory;
        this.commandType = _super.commandType;
        this.creationTime = _super.creationTime;
        this.goods = _super.goods;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.packageType = _super.packageType;
        this.sourceLocation = inits.isInitialized("sourceLocation") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("sourceLocation"), inits.get("sourceLocation")) : null;
        this.stage = _super.stage;
        this.targetLocation = inits.isInitialized("targetLocation") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("targetLocation"), inits.get("targetLocation")) : null;
        this.warehouse = _super.warehouse;
        this.workMode = _super.workMode;
    }

}

