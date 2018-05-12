package org.thinking.logistics.services.core.domain.container.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.container.Totebox;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QTotebox is a Querydsl query type for Totebox
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTotebox extends EntityPathBase<Totebox> {

    public static final QTotebox totebox = new QTotebox("totebox");

    private static final long serialVersionUID = -942805770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QContainer _super;

    //inherited
    public final BooleanPath available;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final StringPath no;

    public final org.thinking.logistics.services.core.domain.dsl.QTask task;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QTotebox(String variable) {
        this(Totebox.class, forVariable(variable), INITS);
    }

    public QTotebox(Path<? extends Totebox> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTotebox(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTotebox(PathMetadata metadata, PathInits inits) {
        this(Totebox.class, metadata, inits);
    }

    public QTotebox(Class<? extends Totebox> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QContainer(type, metadata, inits);
        this.available = _super.available;
        this.id = _super.id;
        this.no = _super.no;
        this.task = inits.isInitialized("task") ? new org.thinking.logistics.services.core.domain.dsl.QTask(forProperty("task"), inits.get("task")) : null;
        this.warehouse = _super.warehouse;
    }

}

