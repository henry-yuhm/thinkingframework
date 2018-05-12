package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.ReplenishingCommand;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QReplenishingCommand is a Querydsl query type for ReplenishingCommand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReplenishingCommand extends EntityPathBase<ReplenishingCommand> {

    public static final QReplenishingCommand replenishingCommand = new QReplenishingCommand("replenishingCommand");

    private static final long serialVersionUID = 279418832L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QTransitionCommand _super;

    public final NumberPath<java.math.BigDecimal> availableQuantity = createNumber("availableQuantity", java.math.BigDecimal.class);

    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory> commandCategory;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandType> commandType;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QReplenishingDetail detail;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QReplenishingHeader header;

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

    public QReplenishingCommand(String variable) {
        this(ReplenishingCommand.class, forVariable(variable), INITS);
    }

    public QReplenishingCommand(Path<? extends ReplenishingCommand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplenishingCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplenishingCommand(PathMetadata metadata, PathInits inits) {
        this(ReplenishingCommand.class, metadata, inits);
    }

    public QReplenishingCommand(Class<? extends ReplenishingCommand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTransitionCommand(type, metadata, inits);
        this.batches = inits.isInitialized("batches") ? new org.thinking.logistics.services.core.domain.dsl.QBatches(forProperty("batches"), inits.get("batches")) : null;
        this.commandCategory = _super.commandCategory;
        this.commandType = _super.commandType;
        this.creationTime = _super.creationTime;
        this.detail = inits.isInitialized("detail") ? new org.thinking.logistics.services.core.domain.bill.dsl.QReplenishingDetail(forProperty("detail"), inits.get("detail")) : null;
        this.goods = _super.goods;
        this.header = inits.isInitialized("header") ? new org.thinking.logistics.services.core.domain.bill.dsl.QReplenishingHeader(forProperty("header"), inits.get("header")) : null;
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

