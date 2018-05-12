package org.thinking.logistics.services.core.domain.command.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.command.OutboundCommand;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QOutboundCommand is a Querydsl query type for OutboundCommand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOutboundCommand extends EntityPathBase<OutboundCommand> {

    public static final QOutboundCommand outboundCommand = new QOutboundCommand("outboundCommand");

    private static final long serialVersionUID = 1446868956L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QCommand _super;

    public final BooleanPath activated = createBoolean("activated");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.AppendantSign> appendantSign = createEnum("appendantSign", org.thinking.logistics.services.core.domain.support.AppendantSign.class);

    public final org.thinking.logistics.services.core.domain.barcode.dsl.QOutboundBarcode barcode;

    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory> commandCategory;

    public final SetPath<org.thinking.logistics.services.core.domain.command.ReplenishingCommand, QReplenishingCommand> commands = this.<org.thinking.logistics.services.core.domain.command.ReplenishingCommand, QReplenishingCommand>createSet("commands", org.thinking.logistics.services.core.domain.command.ReplenishingCommand.class, QReplenishingCommand.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandType> commandType;

    public final NumberPath<java.math.BigDecimal> creationPieces = createNumber("creationPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> creationQuantity = createNumber("creationQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> creationRemainder = createNumber("creationRemainder", java.math.BigDecimal.class);

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QOutboundDetail detail;

    public final NumberPath<java.math.BigDecimal> factPieces = createNumber("factPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factQuantity = createNumber("factQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> factRemainder = createNumber("factRemainder", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    public final org.thinking.logistics.services.core.domain.bill.dsl.QOutboundHeader header;

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InventoryState> inventoryState = createEnum("inventoryState", org.thinking.logistics.services.core.domain.support.InventoryState.class);

    public final org.thinking.logistics.services.core.domain.dsl.QLocation location;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType;

    public final org.thinking.logistics.services.core.domain.container.dsl.QPallet pallet;

    public final QOutboundCommand parent;

    public final StringPath pickingOrder = createString("pickingOrder");

    public final NumberPath<java.math.BigDecimal> planPieces = createNumber("planPieces", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planQuantity = createNumber("planQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planRemainder = createNumber("planRemainder", java.math.BigDecimal.class);

    public final org.thinking.logistics.services.core.domain.dsl.QPlatform platform;

    public final NumberPath<java.math.BigDecimal> remainder = createNumber("remainder", java.math.BigDecimal.class);

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.CommandStage> stage;

    public final org.thinking.logistics.services.core.domain.dsl.QTask task;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.WorkMode> workMode;

    public QOutboundCommand(String variable) {
        this(OutboundCommand.class, forVariable(variable), INITS);
    }

    public QOutboundCommand(Path<? extends OutboundCommand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOutboundCommand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOutboundCommand(PathMetadata metadata, PathInits inits) {
        this(OutboundCommand.class, metadata, inits);
    }

    public QOutboundCommand(Class<? extends OutboundCommand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCommand(type, metadata, inits);
        this.barcode = inits.isInitialized("barcode") ? new org.thinking.logistics.services.core.domain.barcode.dsl.QOutboundBarcode(forProperty("barcode"), inits.get("barcode")) : null;
        this.batches = inits.isInitialized("batches") ? new org.thinking.logistics.services.core.domain.dsl.QBatches(forProperty("batches"), inits.get("batches")) : null;
        this.commandCategory = _super.commandCategory;
        this.commandType = _super.commandType;
        this.creationTime = _super.creationTime;
        this.detail = inits.isInitialized("detail") ? new org.thinking.logistics.services.core.domain.bill.dsl.QOutboundDetail(forProperty("detail"), inits.get("detail")) : null;
        this.goods = _super.goods;
        this.header = inits.isInitialized("header") ? new org.thinking.logistics.services.core.domain.bill.dsl.QOutboundHeader(forProperty("header"), inits.get("header")) : null;
        this.id = _super.id;
        this.location = inits.isInitialized("location") ? new org.thinking.logistics.services.core.domain.dsl.QLocation(forProperty("location"), inits.get("location")) : null;
        this.modificationTime = _super.modificationTime;
        this.packageType = _super.packageType;
        this.pallet = inits.isInitialized("pallet") ? new org.thinking.logistics.services.core.domain.container.dsl.QPallet(forProperty("pallet"), inits.get("pallet")) : null;
        this.parent = inits.isInitialized("parent") ? new QOutboundCommand(forProperty("parent"), inits.get("parent")) : null;
        this.platform = inits.isInitialized("platform") ? new org.thinking.logistics.services.core.domain.dsl.QPlatform(forProperty("platform"), inits.get("platform")) : null;
        this.stage = _super.stage;
        this.task = inits.isInitialized("task") ? new org.thinking.logistics.services.core.domain.dsl.QTask(forProperty("task"), inits.get("task")) : null;
        this.warehouse = _super.warehouse;
        this.workMode = _super.workMode;
    }

}

