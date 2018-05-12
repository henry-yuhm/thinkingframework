package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.Owner;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QOwner is a Querydsl query type for Owner
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOwner extends EntityPathBase<Owner> {

    public static final QOwner owner = new QOwner("owner");

    private static final long serialVersionUID = 1420439031L;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inventoryUpper = createString("inventoryUpper");

    public final StringPath mnemonicCode = createString("mnemonicCode");

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final StringPath serviceHotline = createString("serviceHotline");

    public final BooleanPath thirdpart = createBoolean("thirdpart");

    public QOwner(String variable) {
        super(Owner.class, forVariable(variable));
    }

    public QOwner(Path<? extends Owner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOwner(PathMetadata metadata) {
        super(Owner.class, metadata);
    }

}

