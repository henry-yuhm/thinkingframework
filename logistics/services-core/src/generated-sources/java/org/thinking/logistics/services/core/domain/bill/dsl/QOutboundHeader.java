package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QOutboundHeader is a Querydsl query type for OutboundHeader
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOutboundHeader extends EntityPathBase<OutboundHeader> {

    public static final QOutboundHeader outboundHeader = new QOutboundHeader("outboundHeader");

    private static final long serialVersionUID = 73302760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QHeader _super;

    public final org.thinking.logistics.services.core.domain.dsl.QAddress address;

    public final BooleanPath auditing = createBoolean("auditing");

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee businessman;

    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee buyer;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> category;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final org.thinking.logistics.services.core.domain.dsl.QCustomer customer;

    public final SetPath<org.thinking.logistics.services.core.domain.bill.OutboundDetail, QOutboundDetail> details = this.<org.thinking.logistics.services.core.domain.bill.OutboundDetail, QOutboundDetail>createSet("details", org.thinking.logistics.services.core.domain.bill.OutboundDetail.class, QOutboundDetail.class, PathInits.DIRECT2);

    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee dispatchers;

    public final DatePath<java.sql.Date> dispatcherTime = createDate("dispatcherTime", java.sql.Date.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.DispatcherType> dispatcherType = createEnum("dispatcherType", org.thinking.logistics.services.core.domain.support.DispatcherType.class);

    public final StringPath distributionType = createString("distributionType");

    public final NumberPath<java.math.BigDecimal> equivalentPieces = createNumber("equivalentPieces", java.math.BigDecimal.class);

    public final BooleanPath gatheringComplete = createBoolean("gatheringComplete");

    public final NumberPath<Integer> goodsQuantity = createNumber("goodsQuantity", Integer.class);

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath inversed = createBoolean("inversed");

    public final DatePath<java.sql.Date> invoiceTime = createDate("invoiceTime", java.sql.Date.class);

    //inherited
    public final DatePath<java.sql.Date> modificationTime;

    //inherited
    public final StringPath no;

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee operator;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final QOutboundHeader parent;

    public final BooleanPath printContract = createBoolean("printContract");

    public final NumberPath<Integer> printTimes = createNumber("printTimes", Integer.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.OutboundPriority> priority = createEnum("priority", org.thinking.logistics.services.core.domain.support.OutboundPriority.class);

    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee recheckbillPrintClerk;

    public final StringPath recheckbillPrintSign = createString("recheckbillPrintSign");

    public final DatePath<java.sql.Date> recheckbillPrintTime = createDate("recheckbillPrintTime", java.sql.Date.class);

    public final DatePath<java.sql.Date> recheckCompleteTime = createDate("recheckCompleteTime", java.sql.Date.class);

    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee rechecker;

    public final DatePath<java.sql.Date> recheckStartTime = createDate("recheckStartTime", java.sql.Date.class);

    public final DatePath<java.sql.Date> releaseTime = createDate("releaseTime", java.sql.Date.class);

    //inherited
    public final StringPath remarks;

    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee reportbillPrintClerk;

    public final StringPath reportbillPrintSign = createString("reportbillPrintSign");

    public final DatePath<java.sql.Date> reportbillPrintTime = createDate("reportbillPrintTime", java.sql.Date.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.SaleType> saleType = createEnum("saleType", org.thinking.logistics.services.core.domain.support.SaleType.class);

    public final DatePath<java.sql.Date> settlementTime = createDate("settlementTime", java.sql.Date.class);

    public final StringPath settlementType = createString("settlementType");

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillSource> source;

    public final org.thinking.logistics.services.core.domain.stagingarea.dsl.QStagingarea sourceStagingarea;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.OutboundStage> stage = createEnum("stage", org.thinking.logistics.services.core.domain.support.OutboundStage.class);

    public final BooleanPath stagingareaCleaned = createBoolean("stagingareaCleaned");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TakegoodsMode> takegoodsMode = createEnum("takegoodsMode", org.thinking.logistics.services.core.domain.support.TakegoodsMode.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TakegoodsMode> takegoodsModeSwitch = createEnum("takegoodsModeSwitch", org.thinking.logistics.services.core.domain.support.TakegoodsMode.class);

    public final org.thinking.logistics.services.core.domain.stagingarea.dsl.QStagingarea targetStagingarea;

    public final DatePath<java.sql.Date> taskCompleteTime = createDate("taskCompleteTime", java.sql.Date.class);

    public final StringPath taxName = createString("taxName");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TaxType> taxType = createEnum("taxType", org.thinking.logistics.services.core.domain.support.TaxType.class);

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillType> type;

    public final BooleanPath uploaded = createBoolean("uploaded");

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public final StringPath wave = createString("wave");

    public QOutboundHeader(String variable) {
        this(OutboundHeader.class, forVariable(variable), INITS);
    }

    public QOutboundHeader(Path<? extends OutboundHeader> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOutboundHeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOutboundHeader(PathMetadata metadata, PathInits inits) {
        this(OutboundHeader.class, metadata, inits);
    }

    public QOutboundHeader(Class<? extends OutboundHeader> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QHeader(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new org.thinking.logistics.services.core.domain.dsl.QAddress(forProperty("address"), inits.get("address")) : null;
        this.businessman = _super.businessman;
        this.buyer = inits.isInitialized("buyer") ? new org.thinking.logistics.services.core.domain.employee.dsl.QEmployee(forProperty("buyer"), inits.get("buyer")) : null;
        this.category = _super.category;
        this.creationTime = _super.creationTime;
        this.customer = inits.isInitialized("customer") ? new org.thinking.logistics.services.core.domain.dsl.QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.dispatchers = inits.isInitialized("dispatchers") ? new org.thinking.logistics.services.core.domain.employee.dsl.QEmployee(forProperty("dispatchers"), inits.get("dispatchers")) : null;
        this.id = _super.id;
        this.modificationTime = _super.modificationTime;
        this.no = _super.no;
        this.operator = _super.operator;
        this.owner = _super.owner;
        this.parent = inits.isInitialized("parent") ? new QOutboundHeader(forProperty("parent"), inits.get("parent")) : null;
        this.recheckbillPrintClerk = inits.isInitialized("recheckbillPrintClerk") ? new org.thinking.logistics.services.core.domain.employee.dsl.QEmployee(forProperty("recheckbillPrintClerk"), inits.get("recheckbillPrintClerk")) : null;
        this.rechecker = inits.isInitialized("rechecker") ? new org.thinking.logistics.services.core.domain.employee.dsl.QEmployee(forProperty("rechecker"), inits.get("rechecker")) : null;
        this.remarks = _super.remarks;
        this.reportbillPrintClerk = inits.isInitialized("reportbillPrintClerk") ? new org.thinking.logistics.services.core.domain.employee.dsl.QEmployee(forProperty("reportbillPrintClerk"), inits.get("reportbillPrintClerk")) : null;
        this.source = _super.source;
        this.sourceStagingarea = inits.isInitialized("sourceStagingarea") ? new org.thinking.logistics.services.core.domain.stagingarea.dsl.QStagingarea(forProperty("sourceStagingarea"), inits.get("sourceStagingarea")) : null;
        this.targetStagingarea = inits.isInitialized("targetStagingarea") ? new org.thinking.logistics.services.core.domain.stagingarea.dsl.QStagingarea(forProperty("targetStagingarea"), inits.get("targetStagingarea")) : null;
        this.type = _super.type;
        this.warehouse = _super.warehouse;
    }

}

