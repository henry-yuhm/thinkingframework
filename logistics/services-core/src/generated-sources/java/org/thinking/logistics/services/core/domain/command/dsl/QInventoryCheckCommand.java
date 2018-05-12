package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.InventoryCheckCommand;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInventoryCheckCommand is a Querydsl query type for InventoryCheckCommand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInventoryCheckCommand extends EntityPathBase<InventoryCheckCommand> {

    public static final QInventoryCheckCommand inventoryCheckCommand = new QInventoryCheckCommand("inventoryCheckCommand");

    private static final long serialVersionUID = -1898432128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QTransitionCommand _super;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory> commandCategory;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandType> commandType;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QInventoryCheckDetail detail;

    public final NumberPath<java.math.BigDecimal> factPieces = createNumber("factPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factQuantity = createNumber("factQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factRemainder = createNumber("factRemainder", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QInventoryCheckHeader header;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<java.math.BigDecimal> inventoryPieces = createNumber("inventoryPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> inventoryQuantity = createNumber("inventoryQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> inventoryRemainder = createNumber("inventoryRemainder", java.math.BigDecimal.class);

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType;

    //inherited
    public final BooleanPath sourceActivated;

    public final org.thinking.logistics.services.core.domain.dsl.QBatches sourceBatches;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> sourceInventoryState;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QLocation sourceLocation;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet sourcePallet;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandStage> stage;

    //inherited
    public final BooleanPath targetActivated;

    public final org.thinking.logistics.services.core.domain.dsl.QBatches targetBatches;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> targetInventoryState;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QLocation targetLocation;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet targetPallet;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.WorkMode> workMode;

    public QInventoryCheckCommand(String variable) {
        this(InventoryCheckCommand.class, forVariable(variable), INITS);
    }

    public QInventoryCheckCommand(Path<? extends InventoryCheckCommand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInventoryCheckCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInventoryCheckCommand(PathMetadata metadata, PathInits inits) {
        this(InventoryCheckCommand.class, metadata, inits);
    }

    public QInventoryCheckCommand(Class<? extends InventoryCheckCommand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTransitionCommand(type, metadata, inits);
        this.commandCategory = _super.commandCategory;
        this.commandType = _super.commandType;
        this.creationTime = _super.creationTime;
        this.detail = inits.isInitialized("detail") ? new org.thinking.logistics.services.core.domain.bill.dsl.QInventoryCheckDetail(forProperty("detail"), inits.get("detail")) : null;
        this.goods = _super.goods;
        this.header = inits.isInitialized("header") ? new org.thinking.logistics.services.core.domain.bill.dsl.QInventoryCheckHeader(forProperty("header"), inits.get("header")) : null;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.packageType = _super.packageType;
        this.sourceActivated = _super.sourceActivated;
        this.sourceBatches = inits.isInitialized("sourceBatches") ? new org.thinking.logistics.services.core.domain.dsl.QBatches(forProperty("sourceBatches"), inits.get("sourceBatches")) : null;
        this.sourceInventoryState = _super.sourceInventoryState;
        this.sourceLocation = _super.sourceLocation;
        this.sourcePallet = inits.isInitialized("sourcePallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("sourcePallet"), inits.get("sourcePallet")) : null;
        this.stage = _super.stage;
        this.targetActivated = _super.targetActivated;
        this.targetBatches = inits.isInitialized("targetBatches") ? new org.thinking.logistics.services.core.domain.dsl.QBatches(forProperty("targetBatches"), inits.get("targetBatches")) : null;
        this.targetInventoryState = _super.targetInventoryState;
        this.targetLocation = _super.targetLocation;
        this.targetPallet = inits.isInitialized("targetPallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("targetPallet"), inits.get("targetPallet")) : null;
        this.warehouse = _super.warehouse;
        this.workMode = _super.workMode;
    }

}

