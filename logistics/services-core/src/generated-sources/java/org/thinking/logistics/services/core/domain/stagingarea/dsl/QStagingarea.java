package org.thinking.logistics.services.core.domain.stagingarea.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QStagingarea is a Querydsl query type for Stagingarea
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStagingarea extends EntityPathBase<Stagingarea> {

    public static final QStagingarea stagingarea = new QStagingarea("stagingarea");

    private static final long serialVersionUID = 380367846L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath available = createBoolean("available");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillType> billType = createEnum("billType", org.thinking.logistics.services.core.domain.support.BillType.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StagingareaCategory> category = createEnum("category", org.thinking.logistics.services.core.domain.support.StagingareaCategory.class);

    public final org.thinking.logistics.services.core.domain.dsl.QDirection direction;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath locking = createBoolean("locking");

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final SetPath<org.thinking.logistics.services.core.domain.Owner, org.thinking.logistics.services.core.domain.dsl.QOwner> owners = this.<org.thinking.logistics.services.core.domain.Owner, org.thinking.logistics.services.core.domain.dsl.QOwner>createSet("owners", org.thinking.logistics.services.core.domain.Owner.class, org.thinking.logistics.services.core.domain.dsl.QOwner.class, PathInits.DIRECT2);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TakegoodsMode> takegoodsMode = createEnum("takegoodsMode", org.thinking.logistics.services.core.domain.support.TakegoodsMode.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StagingareaType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.StagingareaType.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QStagingarea(String variable) {
        this(Stagingarea.class, forVariable(variable), INITS);
    }

    public QStagingarea(Path<? extends Stagingarea> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStagingarea(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStagingarea(PathMetadata metadata, PathInits inits) {
        this(Stagingarea.class, metadata, inits);
    }

    public QStagingarea(Class<? extends Stagingarea> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.direction = inits.isInitialized("direction") ? new org.thinking.logistics.services.core.domain.dsl.QDirection(forProperty("direction")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

