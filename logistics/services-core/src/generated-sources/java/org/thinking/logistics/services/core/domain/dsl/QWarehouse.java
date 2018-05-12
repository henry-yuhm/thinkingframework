package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Warehouse;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QWarehouse is a Querydsl query type for Warehouse
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWarehouse extends EntityPathBase<Warehouse> {

    public static final QWarehouse warehouse = new QWarehouse("warehouse");

    private static final long serialVersionUID = -9554937L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final StringPath address = createString("address");

    public final BooleanPath electronicLabel = createBoolean("electronicLabel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QOwner owner;

    public final BooleanPath pallet = createBoolean("pallet");

    public final BooleanPath sorter = createBoolean("sorter");

    public final BooleanPath tablet = createBoolean("tablet");

    public final MapPath<Integer, org.thinking.logistics.services.core.domain.support.TransferlineType, EnumPath<org.thinking.logistics.services.core.domain.support.TransferlineType>> transferlineTypes = this.<Integer, org.thinking.logistics.services.core.domain.support.TransferlineType, EnumPath<org.thinking.logistics.services.core.domain.support.TransferlineType>>createMap("transferlineTypes", Integer.class, org.thinking.logistics.services.core.domain.support.TransferlineType.class, EnumPath.class);

    public final MapPath<Integer, org.thinking.logistics.services.core.domain.support.TWFType, EnumPath<org.thinking.logistics.services.core.domain.support.TWFType>> twfTypes = this.<Integer, org.thinking.logistics.services.core.domain.support.TWFType, EnumPath<org.thinking.logistics.services.core.domain.support.TWFType>>createMap("twfTypes", Integer.class, org.thinking.logistics.services.core.domain.support.TWFType.class, EnumPath.class);

    public QWarehouse(String variable) {
        this(Warehouse.class, forVariable(variable), INITS);
    }

    public QWarehouse(Path<? extends Warehouse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWarehouse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWarehouse(PathMetadata metadata, PathInits inits) {
        this(Warehouse.class, metadata, inits);
    }

    public QWarehouse(Class<? extends Warehouse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QOwner(forProperty("owner")) : null;
    }

}

