package org.thinking.logistics.services.core.domain.barcode.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.barcode.Barcode;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QBarcode is a Querydsl query type for Barcode
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBarcode extends EntityPathBase<Barcode> {

    public static final QBarcode barcode = new QBarcode("barcode");

    private static final long serialVersionUID = -135317674L;

    public final DatePath<java.sql.Date> creationTime = createDate("creationTime", java.sql.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.sql.Date> modificationTime = createDate("modificationTime", java.sql.Date.class);

    public final StringPath no = createString("no");

    public QBarcode(String variable) {
        super(Barcode.class, forVariable(variable));
    }

    public QBarcode(Path<? extends Barcode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBarcode(PathMetadata metadata) {
        super(Barcode.class, metadata);
    }

}

