package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.InventoryCheckHeader;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QInventoryCheckHeader is a Querydsl query type for InventoryCheckHeader
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInventoryCheckHeader extends EntityPathBase<InventoryCheckHeader> {

    public static final QInventoryCheckHeader inventoryCheckHeader = new QInventoryCheckHeader("inventoryCheckHeader");

    private static final long serialVersionUID = -212433980L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QHeader _super;

    // inherited
    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee businessman;

    //inherited
    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> category;

    //inherited
    public final DatePath<java.sql.Date> creationTime;

    public final SetPath<org.thinking.logistics.services.core.domain.bill.InventoryCheckDetail, QInventoryCheckDetail> details = this.<org.thinking.logistics.services.core.domain.bill.InventoryCheckDetail, QInventoryCheckDetail>createSet("details", org.thinking.logistics.services.core.domain.bill.InventoryCheckDetail.class, QInventoryCheckDetail.class, PathInits.DIRECT2);

    public final BooleanPath executed = createBoolean("executed");

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

    public QInventoryCheckHeader(String variable) {
        this(InventoryCheckHeader.class, forVariable(variable), INITS);
    }

    public QInventoryCheckHeader(Path<? extends InventoryCheckHeader> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInventoryCheckHeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInventoryCheckHeader(PathMetadata metadata, PathInits inits) {
        this(InventoryCheckHeader.class, metadata, inits);
    }

    public QInventoryCheckHeader(Class<? extends InventoryCheckHeader> type, PathMetadata metadata, PathInits inits) {
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

