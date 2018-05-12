package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.SorterSlide;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QSorterSlide is a Querydsl query type for SorterSlide
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSorterSlide extends EntityPathBase<SorterSlide> {

    public static final QSorterSlide sorterSlide = new QSorterSlide("sorterSlide");

    private static final long serialVersionUID = 1608382954L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath available = createBoolean("available");

    public final MapPath<Integer, org.thinking.logistics.services.core.domain.support.BillCategory, EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory>> billCategories = this.<Integer, org.thinking.logistics.services.core.domain.support.BillCategory, EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory>>createMap("billCategories", Integer.class, org.thinking.logistics.services.core.domain.support.BillCategory.class, EnumPath.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath no = createString("no");

    public final MapPath<Integer, org.thinking.logistics.services.core.domain.support.CommandCategory, EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory>> taskCategories = this.<Integer, org.thinking.logistics.services.core.domain.support.CommandCategory, EnumPath<org.thinking.logistics.services.core.domain.support.CommandCategory>>createMap("taskCategories", Integer.class, org.thinking.logistics.services.core.domain.support.CommandCategory.class, EnumPath.class);

    public final QWarehouse warehouse;

    public final NumberPath<Integer> workload = createNumber("workload", Integer.class);

    public QSorterSlide(String variable) {
        this(SorterSlide.class, forVariable(variable), INITS);
    }

    public QSorterSlide(Path<? extends SorterSlide> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSorterSlide(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSorterSlide(PathMetadata metadata, PathInits inits) {
        this(SorterSlide.class, metadata, inits);
    }

    public QSorterSlide(Class<? extends SorterSlide> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehouse = inits.isInitialized("warehouse") ? new QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

