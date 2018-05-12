package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Platform;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QPlatform is a Querydsl query type for Platform
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlatform extends EntityPathBase<Platform> {

    public static final QPlatform platform = new QPlatform("platform");

    private static final long serialVersionUID = -1801963857L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final QWarehouse warehouse;

    public QPlatform(String variable) {
        this(Platform.class, forVariable(variable), INITS);
    }

    public QPlatform(Path<? extends Platform> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlatform(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlatform(PathMetadata metadata, PathInits inits) {
        this(Platform.class, metadata, inits);
    }

    public QPlatform(Class<? extends Platform> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehouse = inits.isInitialized("warehouse") ? new QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

