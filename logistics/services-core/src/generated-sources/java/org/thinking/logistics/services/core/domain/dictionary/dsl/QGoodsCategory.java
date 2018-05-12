package org.thinking.logistics.services.core.domain.dictionary.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.dictionary.GoodsCategory;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QGoodsCategory is a Querydsl query type for GoodsCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsCategory extends EntityPathBase<GoodsCategory> {

    public static final QGoodsCategory goodsCategory = new QGoodsCategory("goodsCategory");

    private static final long serialVersionUID = -1273048808L;

    public final QDictionary _super = new QDictionary(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath no = _super.no;

    public QGoodsCategory(String variable) {
        super(GoodsCategory.class, forVariable(variable));
    }

    public QGoodsCategory(Path<? extends GoodsCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsCategory(PathMetadata metadata) {
        super(GoodsCategory.class, metadata);
    }

}

