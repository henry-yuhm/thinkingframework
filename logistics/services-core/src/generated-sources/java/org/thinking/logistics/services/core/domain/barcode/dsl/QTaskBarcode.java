package org.thinking.logistics.services.core.domain.barcode.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.barcode.TaskBarcode;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTaskBarcode is a Querydsl query type for TaskBarcode
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QTaskBarcode extends EntityPathBase<TaskBarcode> {

    public static final QTaskBarcode taskBarcode = new QTaskBarcode("taskBarcode");

    private static final long serialVersionUID = 1860759313L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QBarcode _super = new QBarcode(this);

    //inherited
    public final DatePath<java.sql.Date> creationTime = _super.creationTime;

    public final StringPath deviceNo = createString("deviceNo");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime = _super.modificationTime;

    //inherited
    public final StringPath no = _super.no;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TransmissionType> transmissionType = createEnum("transmissionType", org.thinking.logistics.services.core.domain.support.TransmissionType.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BarcodeType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.BarcodeType.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QTaskBarcode(String variable) {
        this(TaskBarcode.class, forVariable(variable), INITS);
    }

    public QTaskBarcode(Path<? extends TaskBarcode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskBarcode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskBarcode(PathMetadata metadata, PathInits inits) {
        this(TaskBarcode.class, metadata, inits);
    }

    public QTaskBarcode(Class<? extends TaskBarcode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

