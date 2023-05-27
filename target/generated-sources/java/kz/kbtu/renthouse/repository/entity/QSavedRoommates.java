package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSavedRoommates is a Querydsl query type for SavedRoommates
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSavedRoommates extends EntityPathBase<SavedRoommates> {

    private static final long serialVersionUID = -1723961903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSavedRoommates savedRoommates = new QSavedRoommates("savedRoommates");

    public final DatePath<java.time.LocalDate> addedAt = createDate("addedAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final QUser savedUsers;

    public final QUser user;

    public QSavedRoommates(String variable) {
        this(SavedRoommates.class, forVariable(variable), INITS);
    }

    public QSavedRoommates(Path<? extends SavedRoommates> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSavedRoommates(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSavedRoommates(PathMetadata metadata, PathInits inits) {
        this(SavedRoommates.class, metadata, inits);
    }

    public QSavedRoommates(Class<? extends SavedRoommates> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.savedUsers = inits.isInitialized("savedUsers") ? new QUser(forProperty("savedUsers")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

