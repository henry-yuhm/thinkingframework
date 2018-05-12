package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.InboundHeader;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInboundHeader is a Querydsl query type for InboundHeader
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInboundHeader extends EntityPathBase<InboundHeader> {

    public static final QInboundHeader inboundHeader = new QInboundHeader("inboundHeader");

    private static final long serialVersionUID = -1490544453L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QHeader _super;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.ArrivalMode> arrivalMode = createEnum("arrivalMode", org.thinking.logistics.services.core.domain.support.ArrivalMode.class);

    public final StringPath arrivalNo = createString("arrivalNo");

    public final DatePath<java.sql.Date> arrivalTime = createDate("arrivalTime", java.sql.Date.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.ArrivalVoucher> arrivalVoucher = createEnum("arrivalVoucher", org.thinking.logistics.services.core.domain.support.ArrivalVoucher.class);

    public final StringPath auditor = createString("auditor");

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee businessman;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> category;

    public final BooleanPath chargeup = createBoolean("chargeup");

    public final BooleanPath complete = createBoolean("complete");

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final SetPath<org.thinking.logistics.services.core.domain.bill.InboundDetail, QInboundDetail> details = this.<org.thinking.logistics.services.core.domain.bill.InboundDetail, QInboundDetail>createSet("details", org.thinking.logistics.services.core.domain.bill.InboundDetail.class, QInboundDetail.class, PathInits.DIRECT2);

    public final BooleanPath executed = createBoolean("executed");

    //inherited
    public final NumberPath<Long> id;

    public final StringPath inspector = createString("inspector");

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final StringPath no;

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee operator;

    public final QPurchaseOrderHeader order;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final BooleanPath passback = createBoolean("passback");

    public final BooleanPath printed = createBoolean("printed");

    public final StringPath receivingClerk = createString("receivingClerk");

    //inherited
    public final StringPath remarks;

    public final StringPath saleOrderNo = createString("saleOrderNo");

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillSource> source;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InboundStage> stage = createEnum("stage", org.thinking.logistics.services.core.domain.support.InboundStage.class);

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QInboundHeader(String variable) {
        this(InboundHeader.class, forVariable(variable), INITS);
    }

    public QInboundHeader(Path<? extends InboundHeader> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInboundHeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInboundHeader(PathMetadata metadata, PathInits inits) {
        this(InboundHeader.class, metadata, inits);
    }

    public QInboundHeader(Class<? extends InboundHeader> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QHeader(type, metadata, inits);
        this.businessman = _super.businessman;
        this.category = _super.category;
        this.creationTime = _super.creationTime;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.no = _super.no;
        this.operator = _super.operator;
        this.order = inits.isInitialized("order") ? new QPurchaseOrderHeader(forProperty("order"), inits.get("order")) : null;
        this.owner = _super.owner;
        this.remarks = _super.remarks;
        this.source = _super.source;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

