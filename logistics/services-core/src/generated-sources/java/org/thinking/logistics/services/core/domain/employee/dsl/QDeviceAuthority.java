package org.thinking.logistics.services.core.domain.employee.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.employee.DeviceAuthority;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QDeviceAuthority is a Querydsl query type for DeviceAuthority
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceAuthority extends EntityPathBase<DeviceAuthority> {

    public static final QDeviceAuthority deviceAuthority = new QDeviceAuthority("deviceAuthority");

    private static final long serialVersionUID = -576857015L;

    public final BooleanPath gathering = createBoolean("gathering");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath picking = createBoolean("picking");

    public final BooleanPath rechecking = createBoolean("rechecking");

    public QDeviceAuthority(String variable) {
        super(DeviceAuthority.class, forVariable(variable));
    }

    public QDeviceAuthority(Path<? extends DeviceAuthority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceAuthority(PathMetadata metadata) {
        super(DeviceAuthority.class, metadata);
    }

}

