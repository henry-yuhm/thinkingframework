package org.thinking.logistics.services.core.domain.parameter.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.parameter.Parameter;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QParameter is a Querydsl query type for Parameter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParameter extends EntityPathBase<Parameter> {

    public static final QParameter parameter = new QParameter("parameter");

    private static final long serialVersionUID = 419399656L;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final SetPath<org.thinking.logistics.services.core.domain.parameter.ParameterRange, QParameterRange> ranges = this.<org.thinking.logistics.services.core.domain.parameter.ParameterRange, QParameterRange>createSet("ranges", org.thinking.logistics.services.core.domain.parameter.ParameterRange.class, QParameterRange.class, PathInits.DIRECT2);

    public final StringPath remarks = createString("remarks");

    public final StringPath sign = createString("sign");

    public final StringPath value = createString("value");

    public QParameter(String variable) {
        super(Parameter.class, forVariable(variable));
    }

    public QParameter(Path<? extends Parameter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParameter(PathMetadata metadata) {
        super(Parameter.class, metadata);
    }

}

