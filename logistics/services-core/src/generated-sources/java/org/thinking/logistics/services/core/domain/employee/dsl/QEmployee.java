package org.thinking.logistics.services.core.domain.employee.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathInits;
import org.thinking.logistics.services.core.domain.employee.Employee;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    public static final QEmployee employee = new QEmployee("employee");

    private static final long serialVersionUID = -1278005390L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public final BooleanPath activated = createBoolean("activated");

    public final SetPath<org.thinking.logistics.services.core.domain.employee.DeviceAuthentication, QDeviceAuthentication> authentications = this.<org.thinking.logistics.services.core.domain.employee.DeviceAuthentication, QDeviceAuthentication>createSet("authentications", org.thinking.logistics.services.core.domain.employee.DeviceAuthentication.class, QDeviceAuthentication.class, PathInits.DIRECT2);

    public final QDeviceAuthority authority;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mnemonicCode = createString("mnemonicCode");

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final org.thinking.logistics.services.core.domain.dsl.QOwner owner;

    public final SetPath<org.thinking.logistics.services.core.domain.Owner, org.thinking.logistics.services.core.domain.dsl.QOwner> owners = this.<org.thinking.logistics.services.core.domain.Owner, org.thinking.logistics.services.core.domain.dsl.QOwner>createSet("owners", org.thinking.logistics.services.core.domain.Owner.class, org.thinking.logistics.services.core.domain.dsl.QOwner.class, PathInits.DIRECT2);

    public final SetPath<org.thinking.logistics.services.core.domain.dictionary.EmployeePost, org.thinking.logistics.services.core.domain.dictionary.dsl.QEmployeePost> posts = this.<org.thinking.logistics.services.core.domain.dictionary.EmployeePost, org.thinking.logistics.services.core.domain.dictionary.dsl.QEmployeePost>createSet("posts", org.thinking.logistics.services.core.domain.dictionary.EmployeePost.class, org.thinking.logistics.services.core.domain.dictionary.dsl.QEmployeePost.class, PathInits.DIRECT2);

    public final SetPath<org.thinking.logistics.services.core.domain.dictionary.EmployeeRole, org.thinking.logistics.services.core.domain.dictionary.dsl.QEmployeeRole> roles = this.<org.thinking.logistics.services.core.domain.dictionary.EmployeeRole, org.thinking.logistics.services.core.domain.dictionary.dsl.QEmployeeRole>createSet("roles", org.thinking.logistics.services.core.domain.dictionary.EmployeeRole.class, org.thinking.logistics.services.core.domain.dictionary.dsl.QEmployeeRole.class, PathInits.DIRECT2);

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployee(PathMetadata metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.authority = inits.isInitialized("authority") ? new QDeviceAuthority(forProperty("authority")) : null;
        this.owner = inits.isInitialized("owner") ? new org.thinking.logistics.services.core.domain.dsl.QOwner(forProperty("owner")) : null;
    }

}

