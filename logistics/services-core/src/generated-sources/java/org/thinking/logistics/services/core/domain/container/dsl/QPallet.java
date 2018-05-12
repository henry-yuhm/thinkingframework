package org.thinking.logistics.services.core.domain.container.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.container.Pallet;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QPallet is a Querydsl query type for Pallet
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPallet extends EntityPathBase<Pallet> {

    public static final QPallet pallet = new QPallet("pallet");

    private static final long serialVersionUID = -989374487L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QContainer _super;

    //inherited
    public final BooleanPath available;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final StringPath no;

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QPallet(String variable) {
        this(Pallet.class, forVariable(variable), INITS);
    }

    public QPallet(Path<? extends Pallet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPallet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPallet(PathMetadata metadata, PathInits inits) {
        this(Pallet.class, metadata, inits);
    }

    public QPallet(Class<? extends Pallet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QContainer(type, metadata, inits);
        this.available = _super.available;
        this.id = _super.id;
        this.no = _super.no;
        this.warehouse = _super.warehouse;
    }

}

