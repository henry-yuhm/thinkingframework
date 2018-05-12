package org.thinking.logistics.services.core.domain.barcode.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.barcode.OutboundBarcode;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QOutboundBarcode is a Querydsl query type for OutboundBarcode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOutboundBarcode extends EntityPathBase<OutboundBarcode> {

    public static final QOutboundBarcode outboundBarcode = new QOutboundBarcode("outboundBarcode");

    private static final long serialVersionUID = 2032566342L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QTaskBarcode _super;

    public final BooleanPath available = createBoolean("available");

    public final SetPath<org.thinking.logistics.services.core.domain.command.OutboundCommand, org.thinking.logistics.services.core.domain.command.dsl.QOutboundCommand> commands = this.<org.thinking.logistics.services.core.domain.command.OutboundCommand, org.thinking.logistics.services.core.domain.command.dsl.QOutboundCommand>createSet("commands", org.thinking.logistics.services.core.domain.command.OutboundCommand.class, org.thinking.logistics.services.core.domain.command.dsl.QOutboundCommand.class, PathInits.DIRECT2);

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    //inherited
    public final StringPath deviceNo;

    public final BooleanPath gathered = createBoolean("gathered");

    public final StringPath groupage = createString("groupage");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.GroupageType> groupageType = createEnum("groupageType", org.thinking.logistics.services.core.domain.support.GroupageType.class);

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final StringPath no;

    public final org.thinking.logistics.services.core.domain.dsl.QSorterSlide slide;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.TransmissionType> transmissionType;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BarcodeType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QOutboundBarcode(String variable) {
        this(OutboundBarcode.class, forVariable(variable), INITS);
    }

    public QOutboundBarcode(Path<? extends OutboundBarcode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOutboundBarcode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOutboundBarcode(PathMetadata metadata, PathInits inits) {
        this(OutboundBarcode.class, metadata, inits);
    }

    public QOutboundBarcode(Class<? extends OutboundBarcode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTaskBarcode(type, metadata, inits);
        this.creationTime = _super.creationTime;
        this.deviceNo = _super.deviceNo;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.no = _super.no;
        this.slide = inits.isInitialized("slide") ? new org.thinking.logistics.services.core.domain.dsl.QSorterSlide(forProperty("slide"), inits.get("slide")) : null;
        this.transmissionType = _super.transmissionType;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

