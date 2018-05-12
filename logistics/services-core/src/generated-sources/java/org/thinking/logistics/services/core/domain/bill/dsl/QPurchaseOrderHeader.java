package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.PurchaseOrderHeader;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QPurchaseOrderHeader is a Querydsl query type for PurchaseOrderHeader
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPurchaseOrderHeader extends EntityPathBase<PurchaseOrderHeader> {

    public static final QPurchaseOrderHeader purchaseOrderHeader = new QPurchaseOrderHeader("purchaseOrderHeader");

    private static final long serialVersionUID = 411641167L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QHeader _super;

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee businessman;

    public final StringPath buyer = createString("buyer");

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> category;

    public final BooleanPath complete = createBoolean("complete");

    public final StringPath contactName = createString("contactName");

    public final StringPath contactPhone = createString("contactPhone");

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.dsl.QCustomer customer;

    public final StringPath departureLocation = createString("departureLocation");

    public final SetPath<org.thinking.logistics.services.core.domain.bill.PurchaseOrderDetail, QPurchaseOrderDetail> details = this.<org.thinking.logistics.services.core.domain.bill.PurchaseOrderDetail, QPurchaseOrderDetail>createSet("details", org.thinking.logistics.services.core.domain.bill.PurchaseOrderDetail.class, QPurchaseOrderDetail.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.InboundType> inboundType = createEnum("inboundType", org.thinking.logistics.services.core.domain.support.InboundType.class);

    public final DatePath<java.sql.Date> invoiceTime = createDate("invoiceTime", java.sql.Date.class);

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

    public final StringPath shipper = createString("shipper");

    public final StringPath shippingMode = createString("shippingMode");

    public final DatePath<java.sql.Date> shippingTime = createDate("shippingTime", java.sql.Date.class);

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillSource> source;

    public final StringPath tempControlMode = createString("tempControlMode");

    public final StringPath tempRecord = createString("tempRecord");

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillType> type;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QPurchaseOrderHeader(String variable) {
        this(PurchaseOrderHeader.class, forVariable(variable), INITS);
    }

    public QPurchaseOrderHeader(Path<? extends PurchaseOrderHeader> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseOrderHeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseOrderHeader(PathMetadata metadata, PathInits inits) {
        this(PurchaseOrderHeader.class, metadata, inits);
    }

    public QPurchaseOrderHeader(Class<? extends PurchaseOrderHeader> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QHeader(type, metadata, inits);
        this.businessman = _super.businessman;
        this.category = _super.category;
        this.creationTime = _super.creationTime;
        this.customer = inits.isInitialized("customer") ? new org.thinking.logistics.services.core.domain.dsl.QCustomer(forProperty("customer"), inits.get("customer")) : null;
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

