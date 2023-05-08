package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSavedHouse is a Querydsl query type for SavedHouse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSavedHouse extends EntityPathBase<SavedHouse> {

    private static final long serialVersionUID = 34740702L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSavedHouse savedHouse = new QSavedHouse("savedHouse");

    public final QHouseEntity house;

    public final StringPath id = createString("id");

    public final QUser user;

    public QSavedHouse(String variable) {
        this(SavedHouse.class, forVariable(variable), INITS);
    }

    public QSavedHouse(Path<? extends SavedHouse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSavedHouse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSavedHouse(PathMetadata metadata, PathInits inits) {
        this(SavedHouse.class, metadata, inits);
    }

    public QSavedHouse(Class<? extends SavedHouse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.house = inits.isInitialized("house") ? new QHouseEntity(forProperty("house"), inits.get("house")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

