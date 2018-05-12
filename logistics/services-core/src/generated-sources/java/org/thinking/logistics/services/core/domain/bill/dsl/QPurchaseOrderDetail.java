package org.thinking.logistics.services.core.domain.bill.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.bill.PurchaseOrderDetail;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QPurchaseOrderDetail is a Querydsl query type for PurchaseOrderDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPurchaseOrderDetail extends EntityPathBase<PurchaseOrderDetail> {

    public static final QPurchaseOrderDetail purchaseOrderDetail = new QPurchaseOrderDetail("purchaseOrderDetail");

    private static final long serialVersionUID = 297687827L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final QDetail _super;

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QBatches batches;

    public final BooleanPath complete = createBoolean("complete");

    public final NumberPath<java.math.BigDecimal> factQuantity = createNumber("factQuantity", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QGoods goods;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<java.math.BigDecimal> planQuantity = createNumber("planQuantity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    // inherited
    public final org.thinking.logistics.services.core.domain.dsl.QWarehouse warehouse;

    public QPurchaseOrderDetail(String variable) {
        this(PurchaseOrderDetail.class, forVariable(variable), INITS);
    }

    public QPurchaseOrderDetail(Path<? extends PurchaseOrderDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseOrderDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseOrderDetail(PathMetadata metadata, PathInits inits) {
        this(PurchaseOrderDetail.class, metadata, inits);
    }

    public QPurchaseOrderDetail(Class<? extends PurchaseOrderDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QDetail(type, metadata, inits);
        this.batches = _super.batches;
        this.goods = _super.goods;
        this.id = _super.id;
        this.warehouse = _super.warehouse;
    }

}

