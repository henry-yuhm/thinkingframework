package org.thinking.logistics.services.core.domain.dictionary.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.dictionary.EmployeeRole;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QEmployeeRole is a Querydsl query type for EmployeeRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployeeRole extends EntityPathBase<EmployeeRole> {

    public static final QEmployeeRole employeeRole = new QEmployeeRole("employeeRole");

    private static final long serialVersionUID = -926375648L;

    public final QDictionary _super = new QDictionary(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath no = _super.no;

    public QEmployeeRole(String variable) {
        super(EmployeeRole.class, forVariable(variable));
    }

    public QEmployeeRole(Path<? extends EmployeeRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployeeRole(PathMetadata metadata) {
        super(EmployeeRole.class, metadata);
    }

}

