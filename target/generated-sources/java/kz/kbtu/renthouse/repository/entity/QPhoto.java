package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoto is a Querydsl query type for Photo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoto extends EntityPathBase<Photo> {

    private static final long serialVersionUID = -129058291L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoto photo = new QPhoto("photo");

    public final QHouseEntity house;

    public final StringPath id = createString("id");

    public final StringPath link = createString("link");

    public final DatePath<java.time.LocalDate> localDate = createDate("localDate", java.time.LocalDate.class);

    public QPhoto(String variable) {
        this(Photo.class, forVariable(variable), INITS);
    }

    public QPhoto(Path<? extends Photo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoto(PathMetadata metadata, PathInits inits) {
        this(Photo.class, metadata, inits);
    }

    public QPhoto(Class<? extends Photo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.house = inits.isInitialized("house") ? new QHouseEntity(forProperty("house"), inits.get("house")) : null;
    }

}

