package org.thinking.logistics.services.core.domain.stagingarea.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.stagingarea.VirtualConfiguration;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QVirtualConfiguration is a Querydsl query type for VirtualConfiguration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVirtualConfiguration extends EntityPathBase<VirtualConfiguration> {

    public static final QVirtualConfiguration virtualConfiguration = new QVirtualConfiguration("virtualConfiguration");

    private static final long serialVersionUID = 1694417101L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath available = createBoolean("available");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> billCategory = createEnum("billCategory", org.thinking.logistics.services.core.domain.support.BillCategory.class);

    public final org.thinking.logistics.services.core.domain.dsl.QDirection direction;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.SaleType> saleType = createEnum("saleType", org.thinking.logistics.services.core.domain.support.SaleType.class);

    public final QStagingarea stagingarea;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StagingareaCategory> stagingareaCategory = createEnum("stagingareaCategory", org.thinking.logistics.services.core.domain.support.StagingareaCategory.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TakegoodsMode> takegoodsMode = createEnum("takegoodsMode", org.thinking.logistics.services.core.domain.support.TakegoodsMode.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QVirtualConfiguration(String variable) {
        this(VirtualConfiguration.class, forVariable(variable), INITS);
    }

    public QVirtualConfiguration(Path<? extends VirtualConfiguration> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVirtualConfiguration(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVirtualConfiguration(PathMetadata metadata, PathInits inits) {
        this(VirtualConfiguration.class, metadata, inits);
    }

    public QVirtualConfiguration(Class<? extends VirtualConfiguration> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.direction = inits.isInitialized("direction") ? new org.thinking.logistics.services.core.domain.dsl.QDirection(forProperty("direction")) : null;
        this.owner = inits.isInitialized("owner") ? new org.thinking.logistics.services.core.domain.dsl.QOwner(forProperty("owner")) : null;
        this.stagingarea = inits.isInitialized("stagingarea") ? new QStagingarea(forProperty("stagingarea"), inits.get("stagingarea")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

