package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.Header;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QHeader is a Querydsl query type for Header
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QHeader extends EntityPathBase<Header> {

    public static final QHeader header = new QHeader("header");

    private static final long serialVersionUID = 46013144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee businessman;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> category = createEnum("category", org.thinking.logistics.services.core.domain.support.BillCategory.class);

    public final DatePath<java.sql.Date> creationTime = createDate("creationTime", java.sql.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.sql.Date> modificationTime = createDate("modificationTime", java.sql.Date.class);

    public final StringPath no = createString("no");

    public final org.thinking.logistics.services.core.domain.employee.dsl.QEmployee operator;

    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final StringPath remarks = createString("remarks");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillSource> source = createEnum("source", org.thinking.logistics.services.core.domain.support.BillSource.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.BillType.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QHeader(String variable) {
        this(Header.class, forVariable(variable), INITS);
    }

    public QHeader(Path<? extends Header> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHeader(PathMetadata metadata, PathInits inits) {
        this(Header.class, metadata, inits);
    }

    public QHeader(Class<? extends Header> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.businessman = inits.isInitialized("businessman") ? new org.thinking.logistics.services.core.domain.employee.dsl.QEmployee(forProperty("businessman"), inits.get("businessman")) : null;
        this.operator = inits.isInitialized("operator") ? new org.thinking.logistics.services.core.domain.employee.dsl.QEmployee(forProperty("operator"), inits.get("operator")) : null;
        this.owner = inits.isInitialized("owner") ? new org.thinking.logistics.services.core.domain.dsl.QOwner(forProperty("owner")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

