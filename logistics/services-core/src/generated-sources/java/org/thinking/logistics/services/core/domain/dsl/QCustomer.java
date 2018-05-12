package org.thinking.logistics.services.core.domain.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.Customer;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    public static final QCustomer customer = new QCustomer("customer");

    private static final long serialVersionUID = 1224494618L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final StringPath address = createString("address");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.BatchesRequest> batchesRequest = createEnum("batchesRequest", org.thinking.logistics.services.core.domain.support.BatchesRequest.class);

    public final StringPath businessman = createString("businessman");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.CustomerClassification> classification = createEnum("classification", org.thinking.logistics.services.core.domain.support.CustomerClassification.class);

    public final StringPath district = createString("district");

    public final StringPath grade = createString("grade");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identifier = createString("identifier");

    public final StringPath mnemonicCode = createString("mnemonicCode");

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final QOwner owner;

    public final QCustomer parent;

    public final StringPath phone = createString("phone");

    public final StringPath seat = createString("seat");

    public final StringPath shortName = createString("shortName");

    public final StringPath sourceCode = createString("sourceCode");

    public final EnumPath<org.thinking.logistics.services.core.domain.support.CustomerType> type = createEnum("type", org.thinking.logistics.services.core.domain.support.CustomerType.class);

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomer(PathMetadata metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QOwner(forProperty("owner")) : null;
        this.parent = inits.isInitialized("parent") ? new QCustomer(forProperty("parent"), inits.get("parent")) : null;
    }

}

