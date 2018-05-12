package org.thinking.logistics.services.core.domain.barcode.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.barcode.GoodsBarcode;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QGoodsBarcode is a Querydsl query type for GoodsBarcode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsBarcode extends EntityPathBase<GoodsBarcode> {

    public static final QGoodsBarcode goodsBarcode = new QGoodsBarcode("goodsBarcode");

    private static final long serialVersionUID = -1380932812L;

    public final QBarcode _super = new QBarcode(this);

    //inherited
    public final DatePath<java.sql.Date> creationTime = _super.creationTime;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DatePath<java.sql.Date> modificationTime = _super.modificationTime;

    //inherited
    public final StringPath no = _super.no;

    public QGoodsBarcode(String variable) {
        super(GoodsBarcode.class, forVariable(variable));
    }

    public QGoodsBarcode(Path<? extends GoodsBarcode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsBarcode(PathMetadata metadata) {
        super(GoodsBarcode.class, metadata);
    }

}

