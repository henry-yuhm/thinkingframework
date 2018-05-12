package org.thinking.logistics.services.core.domain.employee.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.employee.DeviceAuthentication;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QDeviceAuthentication is a Querydsl query type for DeviceAuthentication
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceAuthentication extends EntityPathBase<DeviceAuthentication> {

    public static final QDeviceAuthentication deviceAuthentication = new QDeviceAuthentication("deviceAuthentication");

    private static final long serialVersionUID = 941113650L;

    public final EnumPath<org.thinking.logistics.services.core.domain.support.OperationDevice> device = createEnum("device", org.thinking.logistics.services.core.domain.support.OperationDevice.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath jobNo = createString("jobNo");

    public final StringPath password = createString("password");

    public QDeviceAuthentication(String variable) {
        super(DeviceAuthentication.class, forVariable(variable));
    }

    public QDeviceAuthentication(Path<? extends DeviceAuthentication> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceAuthentication(PathMetadata metadata) {
        super(DeviceAuthentication.class, metadata);
    }

}

