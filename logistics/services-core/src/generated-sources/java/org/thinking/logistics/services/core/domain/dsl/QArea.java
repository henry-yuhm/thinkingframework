package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Area;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QArea is a Querydsl query type for Area
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArea extends EntityPathBase<Area> {

    public static final QArea area = new QArea("area");

    private static final long serialVersionUID = 876682441L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final NumberPath<Integer> fullloadQuantity = createNumber("fullloadQuantity", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.OperationDevice> operationDevice = createEnum("operationDevice", org.thinking.logistics.services.core.domain.support.OperationDevice.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.PackageType> packageType = createEnum("packageType", org.thinking.logistics.services.core.domain.support.PackageType.class);

    public final BooleanPath prepicking = createBoolean("prepicking");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.RecheckType> recheckType = createEnum("recheckType", org.thinking.logistics.services.core.domain.support.RecheckType.class);

    public final StringPath region = createString("region");

    public final StringPath storeCategory = createString("storeCategory");

    public final StringPath storeNo = createString("storeNo");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.UpshelfMode> upshelfMode = createEnum("upshelfMode", org.thinking.logistics.services.core.domain.support.UpshelfMode.class);

    public final BooleanPath useSorter = createBoolean("useSorter");

    public final QWarehouse warehouse;

    public QArea(String variable) {
        this(Area.class, forVariable(variable), INITS);
    }

    public QArea(Path<? extends Area> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArea(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArea(PathMetadata metadata, PathInits inits) {
        this(Area.class, metadata, inits);
    }

    public QArea(Class<? extends Area> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehouse = inits.isInitialized("warehouse") ? new QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

