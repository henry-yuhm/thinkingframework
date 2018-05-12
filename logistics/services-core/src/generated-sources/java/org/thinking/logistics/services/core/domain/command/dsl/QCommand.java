package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.Command;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QCommand is a Querydsl query type for Command
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QCommand extends EntityPathBase<Command> {

    public static final QCommand command = new QCommand("command");

    private static final long serialVersionUID = 1861036524L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory> commandCategory = createEnum("commandCategory", org.thinking.logistics.services.core.domain.support.CommandCategory.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandType> commandType = createEnum("commandType", org.thinking.logistics.services.core.domain.support.CommandType.class);

    public final DatePath<java.sql.Date> creationTime = createDate("creationTime", java.sql.Date.class);

    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.sql.Date> modificationTime = createDate("modificationTime", java.sql.Date.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType = createEnum("packageType", org.thinking.logistics.services.core.domain.support.PackageType.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandStage> stage = createEnum("stage", org.thinking.logistics.services.core.domain.support.CommandStage.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.WorkMode> workMode = createEnum("workMode", org.thinking.logistics.services.core.domain.support.WorkMode.class);

    public QCommand(String variable) {
        this(Command.class, forVariable(variable), INITS);
    }

    public QCommand(Path<? extends Command> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommand(PathMetadata metadata, PathInits inits) {
        this(Command.class, metadata, inits);
    }

    public QCommand(Class<? extends Command> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goods = inits.isInitialized("goods") ? new org.thinking.logistics.services.core.domain.dsl.QGoods(forProperty("goods"), inits.get("goods")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

