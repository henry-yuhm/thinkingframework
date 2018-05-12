package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.TransferringCommand;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTransferringCommand is a Querydsl query type for TransferringCommand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransferringCommand extends EntityPathBase<TransferringCommand> {

    public static final QTransferringCommand transferringCommand = new QTransferringCommand("transferringCommand");

    private static final long serialVersionUID = -1697165487L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QTransitionCommand _super;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory> commandCategory;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandType> commandType;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QTransferringDetail detail;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QTransferringHeader header;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet pallet;

    //inherited
    public final BooleanPath sourceActivated;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> sourceInventoryState;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QLocation sourceLocation;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandStage> stage;

    //inherited
    public final BooleanPath targetActivated;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> targetInventoryState;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QLocation targetLocation;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.WorkMode> workMode;

    public QTransferringCommand(String variable) {
        this(TransferringCommand.class, forVariable(variable), INITS);
    }

    public QTransferringCommand(Path<? extends TransferringCommand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransferringCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransferringCommand(PathMetadata metadata, PathInits inits) {
        this(TransferringCommand.class, metadata, inits);
    }

    public QTransferringCommand(Class<? extends TransferringCommand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTransitionCommand(type, metadata, inits);
        this.commandCategory = _super.commandCategory;
        this.commandType = _super.commandType;
        this.creationTime = _super.creationTime;
        this.detail = inits.isInitialized("detail") ? new org.thinking.logistics.services.core.domain.bill.dsl.QTransferringDetail(forProperty("detail"), inits.get("detail")) : null;
        this.goods = _super.goods;
        this.header = inits.isInitialized("header") ? new org.thinking.logistics.services.core.domain.bill.dsl.QTransferringHeader(forProperty("header"), inits.get("header")) : null;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.packageType = _super.packageType;
        this.pallet = inits.isInitialized("pallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("pallet"), inits.get("pallet")) : null;
        this.sourceActivated = _super.sourceActivated;
        this.sourceInventoryState = _super.sourceInventoryState;
        this.sourceLocation = _super.sourceLocation;
        this.stage = _super.stage;
        this.targetActivated = _super.targetActivated;
        this.targetInventoryState = _super.targetInventoryState;
        this.targetLocation = _super.targetLocation;
        this.warehouse = _super.warehouse;
        this.workMode = _super.workMode;
    }

}

