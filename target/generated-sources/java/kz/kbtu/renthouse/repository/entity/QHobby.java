package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHobby is a Querydsl query type for Hobby
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHobby extends EntityPathBase<Hobby> {

    private static final long serialVersionUID = -136250963L;

    public static final QHobby hobby = new QHobby("hobby");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public QHobby(String variable) {
        super(Hobby.class, forVariable(variable));
    }

    public QHobby(Path<? extends Hobby> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHobby(PathMetadata metadata) {
        super(Hobby.class, metadata);
    }

}

