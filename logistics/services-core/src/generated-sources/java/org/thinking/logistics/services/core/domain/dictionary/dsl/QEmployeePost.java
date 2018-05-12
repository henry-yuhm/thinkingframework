package org.thinking.logistics.services.core.domain.dictionary.dsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import org.thinking.logistics.services.core.domain.dictionary.EmployeePost;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;


/**
 * QEmployeePost is a Querydsl query type for EmployeePost
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployeePost extends EntityPathBase<EmployeePost> {

    public static final QEmployeePost employeePost = new QEmployeePost("employeePost");

    private static final long serialVersionUID = -926434998L;

    public final QDictionary _super = new QDictionary(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath no = _super.no;

    public QEmployeePost(String variable) {
        super(EmployeePost.class, forVariable(variable));
    }

    public QEmployeePost(Path<? extends EmployeePost> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployeePost(PathMetadata metadata) {
        super(EmployeePost.class, metadata);
    }

}

