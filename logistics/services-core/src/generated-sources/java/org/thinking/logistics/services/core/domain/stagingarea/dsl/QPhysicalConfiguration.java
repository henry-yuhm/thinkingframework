package org.thinking.logistics.services.core.domain.stagingarea.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.stagingarea.PhysicalConfiguration;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QPhysicalConfiguration is a Querydsl query type for PhysicalConfiguration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPhysicalConfiguration extends EntityPathBase<PhysicalConfiguration> {

    public static final QPhysicalConfiguration physicalConfiguration = new QPhysicalConfiguration("physicalConfiguration");

    private static final long serialVersionUID = 911871101L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> billCategory = createEnum("billCategory", org.thinking.logistics.services.core.domain.support.BillCategory.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StagingareaType> stagingareaType = createEnum("stagingareaType", org.thinking.logistics.services.core.domain.support.StagingareaType.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QPhysicalConfiguration(String variable) {
        this(PhysicalConfiguration.class, forVariable(variable), INITS);
    }

    public QPhysicalConfiguration(Path<? extends PhysicalConfiguration> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhysicalConfiguration(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhysicalConfiguration(PathMetadata metadata, PathInits inits) {
        this(PhysicalConfiguration.class, metadata, inits);
    }

    public QPhysicalConfiguration(Class<? extends PhysicalConfiguration> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new org.thinking.logistics.services.core.domain.dsl.QOwner(forProperty("owner")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

