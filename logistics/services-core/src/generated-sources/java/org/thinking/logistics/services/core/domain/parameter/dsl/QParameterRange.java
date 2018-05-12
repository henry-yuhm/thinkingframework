package org.thinking.logistics.services.core.domain.parameter.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.parameter.ParameterRange;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QParameterRange is a Querydsl query type for ParameterRange
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParameterRange extends EntityPathBase<ParameterRange> {

    public static final QParameterRange parameterRange = new QParameterRange("parameterRange");

    private static final long serialVersionUID = -1657638347L;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath value = createString("value");

    public final StringPath warehouse = createString("warehouse");

    public QParameterRange(String variable) {
        super(ParameterRange.class, forVariable(variable));
    }

    public QParameterRange(Path<? extends ParameterRange> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParameterRange(PathMetadata metadata) {
        super(ParameterRange.class, metadata);
    }

}

