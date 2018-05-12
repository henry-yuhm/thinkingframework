package org.thinking.logistics.services.core.domain.scheduler.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.scheduler.BarcodeScheduler;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QBarcodeScheduler is a Querydsl query type for BarcodeScheduler
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBarcodeScheduler extends EntityPathBase<BarcodeScheduler> {

    public static final QBarcodeScheduler barcodeScheduler = new QBarcodeScheduler("barcodeScheduler");

    private static final long serialVersionUID = 186693674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QScheduler _super;

    //inherited
    public final BooleanPath complete;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    public final org.thinking.logistics.services.core.domain.barcode.dsl.QOutboundBarcode outboundBarcode;

    public final org.thinking.logistics.services.core.domain.barcode.dsl.QReplenishingBarcode replenishingBarcode;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.SchedulerType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QBarcodeScheduler(String variable) {
        this(BarcodeScheduler.class, forVariable(variable), INITS);
    }

    public QBarcodeScheduler(Path<? extends BarcodeScheduler> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBarcodeScheduler(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBarcodeScheduler(PathMetadata metadata, PathInits inits) {
        this(BarcodeScheduler.class, metadata, inits);
    }

    public QBarcodeScheduler(Class<? extends BarcodeScheduler> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QScheduler(type, metadata, inits);
        this.complete = _super.complete;
        this.creationTime = _super.creationTime;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.outboundBarcode = inits.isInitialized("outboundBarcode") ? new org.thinking.logistics.services.core.domain.barcode.dsl.QOutboundBarcode(forProperty("outboundBarcode"), inits.get("outboundBarcode")) : null;
        this.replenishingBarcode = inits.isInitialized("replenishingBarcode") ? new org.thinking.logistics.services.core.domain.barcode.dsl.QReplenishingBarcode(forProperty("replenishingBarcode"), inits.get("replenishingBarcode")) : null;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

