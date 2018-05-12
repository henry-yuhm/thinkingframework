package org.thinking.logistics.services.core.domain.inventory.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.inventory.OutboundConfiguration;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QOutboundConfiguration is a Querydsl query type for OutboundConfiguration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOutboundConfiguration extends EntityPathBase<OutboundConfiguration> {

    public static final QOutboundConfiguration outboundConfiguration = new QOutboundConfiguration("outboundConfiguration");

    private static final long serialVersionUID = -637781224L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BillCategory> billCategory = createEnum("billCategory", org.thinking.logistics.services.core.domain.support.BillCategory.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lowerOrder = createNumber("lowerOrder", Integer.class);

    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType = createEnum("packageType", org.thinking.logistics.services.core.domain.support.PackageType.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.SaleType> saleType = createEnum("saleType", org.thinking.logistics.services.core.domain.support.SaleType.class);

    public final StringPath storeCategory = createString("storeCategory");

    public final StringPath storeNo = createString("storeNo");

    public final NumberPath<java.math.BigDecimal> threshold = createNumber("threshold", java.math.BigDecimal.class);

    public final NumberPath<Integer> upperOrder = createNumber("upperOrder", Integer.class);

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QOutboundConfiguration(String variable) {
        this(OutboundConfiguration.class, forVariable(variable), INITS);
    }

    public QOutboundConfiguration(Path<? extends OutboundConfiguration> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOutboundConfiguration(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOutboundConfiguration(PathMetadata metadata, PathInits inits) {
        this(OutboundConfiguration.class, metadata, inits);
    }

    public QOutboundConfiguration(Class<? extends OutboundConfiguration> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new org.thinking.logistics.services.core.domain.dsl.QOwner(forProperty("owner")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

