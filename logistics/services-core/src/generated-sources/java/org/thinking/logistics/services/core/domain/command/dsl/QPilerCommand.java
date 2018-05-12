package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.PilerCommand;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QPilerCommand is a Querydsl query type for PilerCommand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPilerCommand extends EntityPathBase<PilerCommand> {

    public static final QPilerCommand pilerCommand = new QPilerCommand("pilerCommand");

    private static final long serialVersionUID = -753347670L;

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

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandStage> stage;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.WorkMode> workMode;

    public QPilerCommand(String variable) {
        this(PilerCommand.class, forVariable(variable), INITS);
    }

    public QPilerCommand(Path<? extends PilerCommand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPilerCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPilerCommand(PathMetadata metadata, PathInits inits) {
        this(PilerCommand.class, metadata, inits);
    }

    public QPilerCommand(Class<? extends PilerCommand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCommand(type, metadata, inits);
        this.commandCategory = _super.commandCategory;
        this.commandType = _super.commandType;
        this.creationTime = _super.creationTime;
        this.goods = _super.goods;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.packageType = _super.packageType;
        this.stage = _super.stage;
        this.warehouse = _super.warehouse;
        this.workMode = _super.workMode;
    }

}

