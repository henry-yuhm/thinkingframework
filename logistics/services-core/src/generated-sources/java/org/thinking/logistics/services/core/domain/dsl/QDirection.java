package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.Direction;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QDirection is a Querydsl query type for Direction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDirection extends EntityPathBase<Direction> {

    public static final QDirection direction = new QDirection("direction");

    private static final long serialVersionUID = 1025441987L;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public QDirection(String variable) {
        super(Direction.class, forVariable(variable));
    }

    public QDirection(Path<? extends Direction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDirection(PathMetadata metadata) {
        super(Direction.class, metadata);
    }

}

