package org.thinking.logistics.services.core.domain.container.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.container.Container;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QContainer is a Querydsl query type for Container
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QContainer extends EntityPathBase<Container> {

    public static final QContainer container = new QContainer("container");

    private static final long serialVersionUID = 261983128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath available = createBoolean("available");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath no = createString("no");

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QContainer(String variable) {
        this(Container.class, forVariable(variable), INITS);
    }

    public QContainer(Path<? extends Container> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContainer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContainer(PathMetadata metadata, PathInits inits) {
        this(Container.class, metadata, inits);
    }

    public QContainer(Class<? extends Container> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

