package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.InboundCommand;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInboundCommand is a Querydsl query type for InboundCommand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInboundCommand extends EntityPathBase<InboundCommand> {

    public static final QInboundCommand inboundCommand = new QInboundCommand("inboundCommand");

    private static final long serialVersionUID = 329932753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QCommand _super;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory> commandCategory;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandType> commandType;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QInboundDetail detail;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QInboundHeader header;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType;

    public final QInboundCommand parent;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandStage> stage;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.WorkMode> workMode;

    public QInboundCommand(String variable) {
        this(InboundCommand.class, forVariable(variable), INITS);
    }

    public QInboundCommand(Path<? extends InboundCommand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInboundCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInboundCommand(PathMetadata metadata, PathInits inits) {
        this(InboundCommand.class, metadata, inits);
    }

    public QInboundCommand(Class<? extends InboundCommand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCommand(type, metadata, inits);
        this.commandCategory = _super.commandCategory;
        this.commandType = _super.commandType;
        this.creationTime = _super.creationTime;
        this.detail = inits.isInitialized("detail") ? new org.thinking.logistics.services.core.domain.bill.dsl.QInboundDetail(forProperty("detail"), inits.get("detail")) : null;
        this.goods = _super.goods;
        this.header = inits.isInitialized("header") ? new org.thinking.logistics.services.core.domain.bill.dsl.QInboundHeader(forProperty("header"), inits.get("header")) : null;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.packageType = _super.packageType;
        this.parent = inits.isInitialized("parent") ? new QInboundCommand(forProperty("parent"), inits.get("parent")) : null;
        this.stage = _super.stage;
        this.warehouse = _super.warehouse;
        this.workMode = _super.workMode;
    }

}

