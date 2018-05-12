package org.thinking.logistics.services.core.domain.table.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.table.RecheckTable;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QRecheckTable is a Querydsl query type for RecheckTable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRecheckTable extends EntityPathBase<RecheckTable> {

    public static final QRecheckTable recheckTable = new QRecheckTable("recheckTable");

    private static final long serialVersionUID = -829730955L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath automatic = createBoolean("automatic");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.RecheckTableCategory> category = createEnum("category", org.thinking.logistics.services.core.domain.support.RecheckTableCategory.class);

    public final NumberPath<Integer> goodsQuantity = createNumber("goodsQuantity", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath locking = createBoolean("locking");

    public final DatePath<java.sql.Date> modificationTime = createDate("modificationTime", java.sql.Date.class);

    public final StringPath no = createString("no");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.RecheckTableType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.RecheckTableType.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public final NumberPath<Integer> workload = createNumber("workload", Integer.class);

    public QRecheckTable(String variable) {
        this(RecheckTable.class, forVariable(variable), INITS);
    }

    public QRecheckTable(Path<? extends RecheckTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecheckTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecheckTable(PathMetadata metadata, PathInits inits) {
        this(RecheckTable.class, metadata, inits);
    }

    public QRecheckTable(Class<? extends RecheckTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

