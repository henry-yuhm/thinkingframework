package org.thinking.logistics.services.core.domain.barcode.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.barcode.ReplenishingBarcode;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QReplenishingBarcode is a Querydsl query type for ReplenishingBarcode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReplenishingBarcode extends EntityPathBase<ReplenishingBarcode> {

    public static final QReplenishingBarcode replenishingBarcode = new QReplenishingBarcode("replenishingBarcode");

    private static final long serialVersionUID = 132382138L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QTaskBarcode _super;

    public final SetPath<org.thinking.logistics.services.core.domain.command.ReplenishingCommand, org.thinking.logistics.services.core.domain.command.dsl.QReplenishingCommand> commands = this.<org.thinking.logistics.services.core.domain.command.ReplenishingCommand, org.thinking.logistics.services.core.domain.command.dsl.QReplenishingCommand>createSet("commands", org.thinking.logistics.services.core.domain.command.ReplenishingCommand.class, org.thinking.logistics.services.core.domain.command.dsl.QReplenishingCommand.class, PathInits.DIRECT2);

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    //inherited
    public final StringPath deviceNo;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final StringPath no;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.TransmissionType> transmissionType;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BarcodeType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QReplenishingBarcode(String variable) {
        this(ReplenishingBarcode.class, forVariable(variable), INITS);
    }

    public QReplenishingBarcode(Path<? extends ReplenishingBarcode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplenishingBarcode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplenishingBarcode(PathMetadata metadata, PathInits inits) {
        this(ReplenishingBarcode.class, metadata, inits);
    }

    public QReplenishingBarcode(Class<? extends ReplenishingBarcode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTaskBarcode(type, metadata, inits);
        this.creationTime = _super.creationTime;
        this.deviceNo = _super.deviceNo;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.no = _super.no;
        this.transmissionType = _super.transmissionType;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

