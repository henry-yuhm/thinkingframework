package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.Transferline;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTransferline is a Querydsl query type for Transferline
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransferline extends EntityPathBase<Transferline> {

    public static final QTransferline transferline = new QTransferline("transferline");

    private static final long serialVersionUID = 39373467L;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public QTransferline(String variable) {
        super(Transferline.class, forVariable(variable));
    }

    public QTransferline(Path<? extends Transferline> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransferline(PathMetadata metadata) {
        super(Transferline.class, metadata);
    }

}

