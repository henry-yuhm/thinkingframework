package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Location;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QLocation is a Querydsl query type for Location
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLocation extends EntityPathBase<Location> {

    public static final QLocation location = new QLocation("location");

    private static final long serialVersionUID = -1775604239L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QArea area;

    public final BooleanPath automatic = createBoolean("automatic");

    public final BooleanPath available = createBoolean("available");

    public final StringPath floor = createString("floor");

    public final NumberPath<java.math.BigDecimal> height = createNumber("height", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> length = createNumber("length", java.math.BigDecimal.class);

    public final BooleanPath locking = createBoolean("locking");

    public final StringPath no = createString("no");

    public final NumberPath<java.math.BigDecimal> occupationVolume = createNumber("occupationVolume", java.math.BigDecimal.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType = createEnum("packageType", org.thinking.logistics.services.core.domain.support.PackageType.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.PileupType> pileupType = createEnum("pileupType", org.thinking.logistics.services.core.domain.support.PileupType.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.RackType> rackType = createEnum("rackType", org.thinking.logistics.services.core.domain.support.RackType.class);

    public final StringPath region = createString("region");

    public final StringPath roadway = createString("roadway");

    public final StringPath shortno = createString("shortno");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StorageState> storageState = createEnum("storageState", org.thinking.logistics.services.core.domain.support.StorageState.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StorageType> storageType = createEnum("storageType", org.thinking.logistics.services.core.domain.support.StorageType.class);

    public final QTransferline transferline;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.LocationType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.LocationType.class);

    public final NumberPath<java.math.BigDecimal> volume = createNumber("volume", java.math.BigDecimal.class);

    public final QWarehouse warehouse;

    public final NumberPath<java.math.BigDecimal> width = createNumber("width", java.math.BigDecimal.class);

    public final StringPath x = createString("x");

    public final StringPath y = createString("y");

    public final StringPath z = createString("z");

    public QLocation(String variable) {
        this(Location.class, forVariable(variable), INITS);
    }

    public QLocation(Path<? extends Location> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLocation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLocation(PathMetadata metadata, PathInits inits) {
        this(Location.class, metadata, inits);
    }

    public QLocation(Class<? extends Location> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new QArea(forProperty("area"), inits.get("area")) : null;
        this.transferline = inits.isInitialized("transferline") ? new QTransferline(forProperty("transferline")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

