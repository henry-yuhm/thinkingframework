package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Goods;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QGoods is a Querydsl query type for Goods
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoods extends EntityPathBase<Goods> {

    public static final QGoods goods = new QGoods("goods");

    private static final long serialVersionUID = 1412813466L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final StringPath approval = createString("approval");

    public final DateTimePath<java.util.Date> approvalValidUntil = createDateTime("approvalValidUntil", java.util.Date.class);

    public final org.thinking.logistics.services.core.domain.dictionary.dsl.QGoodsCategory category;

    public final NumberPath<java.math.BigDecimal> goodsWeight = createNumber("goodsWeight", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> height = createNumber("height", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> invoiceUnit = createNumber("invoiceUnit", Integer.class);

    public final StringPath largePackageBarcode = createString("largePackageBarcode");

    public final NumberPath<java.math.BigDecimal> largePackageQuantity = createNumber("largePackageQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> largePackageVolume = createNumber("largePackageVolume", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> length = createNumber("length", java.math.BigDecimal.class);

    public final StringPath manufacturer = createString("manufacturer");

    public final StringPath mediumPackageBarcode = createString("mediumPackageBarcode");

    public final NumberPath<java.math.BigDecimal> mediumPackageQuantity = createNumber("mediumPackageQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mediumPackageVolume = createNumber("mediumPackageVolume", java.math.BigDecimal.class);

    public final StringPath mnemonicCode = createString("mnemonicCode");

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final QOwner owner;

    public final StringPath packageUnit = createString("packageUnit");

    public final NumberPath<java.math.BigDecimal> packageWeight = createNumber("packageWeight", java.math.BigDecimal.class);

    public final StringPath producingArea = createString("producingArea");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.SaleClassification> remainderClassification = createEnum("remainderClassification", org.thinking.logistics.services.core.domain.support.SaleClassification.class);

    public final StringPath smallPackageBarcode = createString("smallPackageBarcode");

    public final NumberPath<java.math.BigDecimal> smallPackageQuantity = createNumber("smallPackageQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> smallPackageVolume = createNumber("smallPackageVolume", java.math.BigDecimal.class);

    public final StringPath specification = createString("specification");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.SplittingGranularity> splittingGranularity = createEnum("splittingGranularity", org.thinking.logistics.services.core.domain.support.SplittingGranularity.class);

    public final EnumPath<org.thinking.logistics.services.core.domain.support.StorageClassification> storageClassification = createEnum("storageClassification", org.thinking.logistics.services.core.domain.support.StorageClassification.class);

    public final StringPath storageCondition = createString("storageCondition");

    public final StringPath storageRequest = createString("storageRequest");

    public final NumberPath<java.math.BigDecimal> tcmOutboundQuantity = createNumber("tcmOutboundQuantity", java.math.BigDecimal.class);

    public final StringPath universalName = createString("universalName");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.SaleClassification> wholepiecesClassification = createEnum("wholepiecesClassification", org.thinking.logistics.services.core.domain.support.SaleClassification.class);

    public final NumberPath<java.math.BigDecimal> width = createNumber("width", java.math.BigDecimal.class);

    public QGoods(String variable) {
        this(Goods.class, forVariable(variable), INITS);
    }

    public QGoods(Path<? extends Goods> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoods(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoods(PathMetadata metadata, PathInits inits) {
        this(Goods.class, metadata, inits);
    }

    public QGoods(Class<? extends Goods> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new org.thinking.logistics.services.core.domain.dictionary.dsl.QGoodsCategory(forProperty("category")) : null;
        this.owner = inits.isInitialized("owner") ? new QOwner(forProperty("owner")) : null;
    }

}

