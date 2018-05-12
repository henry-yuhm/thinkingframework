package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.TransferringHeader;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTransferringHeader is a Querydsl query type for TransferringHeader
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransferringHeader extends EntityPathBase<TransferringHeader> {

    public static final QTransferringHeader transferringHeader = new QTransferringHeader("transferringHeader");

    private static final long serialVersionUID = -1129643309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QHeader _super;

    public final StringPath auditor = createString("auditor");

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee businessman;

    public final StringPath cancellingman = createString("cancellingman");

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> category;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final SetPath<org.thinking.logistics.services.core.domain.bill.TransferringDetail, QTransferringDetail> details = this.<org.thinking.logistics.services.core.domain.bill.TransferringDetail, QTransferringDetail>createSet("details", org.thinking.logistics.services.core.domain.bill.TransferringDetail.class, QTransferringDetail.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final StringPath no;

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee operator;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    //inherited
    public final StringPath remarks;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillSource> source;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QTransferringHeader(String variable) {
        this(TransferringHeader.class, forVariable(variable), INITS);
    }

    public QTransferringHeader(Path<? extends TransferringHeader> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransferringHeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransferringHeader(PathMetadata metadata, PathInits inits) {
        this(TransferringHeader.class, metadata, inits);
    }

    public QTransferringHeader(Class<? extends TransferringHeader> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QHeader(type, metadata, inits);
        this.businessman = _super.businessman;
        this.category = _super.category;
        this.creationTime = _super.creationTime;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.no = _super.no;
        this.operator = _super.operator;
        this.owner = _super.owner;
        this.remarks = _super.remarks;
        this.source = _super.source;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

