package org.thinking.logistics.services.core.domain.table.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.table.RecheckSlide;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QRecheckSlide is a Querydsl query type for RecheckSlide
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRecheckSlide extends EntityPathBase<RecheckSlide> {

    public static final QRecheckSlide recheckSlide = new QRecheckSlide("recheckSlide");

    private static final long serialVersionUID = -830320296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath no = createString("no");

    public final QRecheckTable table;

    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QRecheckSlide(String variable) {
        this(RecheckSlide.class, forVariable(variable), INITS);
    }

    public QRecheckSlide(Path<? extends RecheckSlide> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecheckSlide(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecheckSlide(PathMetadata metadata, PathInits inits) {
        this(RecheckSlide.class, metadata, inits);
    }

    public QRecheckSlide(Class<? extends RecheckSlide> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.table = inits.isInitialized("table") ? new QRecheckTable(forProperty("table"), inits.get("table")) : null;
        this.warehouse = inits.isInitialized("warehouse") ? new org.thinking.logistics.services.core.domain.dsl.QWarehouse(forProperty("warehouse"), inits.get("warehouse")) : null;
    }

}

