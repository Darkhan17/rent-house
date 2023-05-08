package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCode is a Querydsl query type for Code
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCode extends EntityPathBase<Code> {

    private static final long serialVersionUID = 272550578L;

    public static final QCode code = new QCode("code");

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final StringPath otpCode = createString("otpCode");

    public QCode(String variable) {
        super(Code.class, forVariable(variable));
    }

    public QCode(Path<? extends Code> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCode(PathMetadata metadata) {
        super(Code.class, metadata);
    }

}

