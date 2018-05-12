package org.thinking.logistics.services.core.domain.dictionary.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.dictionary.Dictionary;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QDictionary is a Querydsl query type for Dictionary
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QDictionary extends EntityPathBase<Dictionary> {

    public static final QDictionary dictionary = new QDictionary("dictionary");

    private static final long serialVersionUID = -772225934L;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public QDictionary(String variable) {
        super(Dictionary.class, forVariable(variable));
    }

    public QDictionary(Path<? extends Dictionary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDictionary(PathMetadata metadata) {
        super(Dictionary.class, metadata);
    }

}

