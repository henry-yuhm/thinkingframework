package org.thinking.logistics.services.core.domain.stagingarea.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.stagingarea.StagingareaConfiguration;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QStagingareaConfiguration is a Querydsl query type for StagingareaConfiguration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStagingareaConfiguration extends EntityPathBase<StagingareaConfiguration> {

    public static final QStagingareaConfiguration stagingareaConfiguration = new QStagingareaConfiguration("stagingareaConfiguration");

    private static final long serialVersionUID = 1840035504L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StagingareaAllocationMode> allocationMode = createEnum("allocationMode", org.thinking.logistics.services.core.domain.support.StagingareaAllocationMode.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> largeQuantity = createNumber("largeQuantity", java.math.BigDecimal.class);

    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final NumberPath<java.math.BigDecimal> smallQuantity = createNumber("smallQuantity", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.TakegoodsMode> takegoodsMode = createEnum("takegoodsMode", org.thinking.logistics.services.core.domain.support.TakegoodsMode.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QStagingareaConfiguration(String variable) {
        this(StagingareaConfiguration.class, forVariable(variable), INITS);
    }

    public QStagingareaConfiguration(Path<? extends StagingareaConfiguration> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStagingareaConfiguration(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStagingareaConfiguration(PathMetadata metadata, PathInits inits) {
        this(StagingareaConfiguration.class, metadata, inits);
    }

    public QStagingareaConfiguration(Class<? extends StagingareaConfiguration> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new org.thinking.logistics.services.core.domain.dsl.QOwner(forProperty("owner")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

