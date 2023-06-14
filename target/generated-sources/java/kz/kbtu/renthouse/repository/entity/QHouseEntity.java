package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHouseEntity is a Querydsl query type for HouseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHouseEntity extends EntityPathBase<HouseEntity> {

    private static final long serialVersionUID = -2144949570L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHouseEntity houseEntity = new QHouseEntity("houseEntity");

    public final kz.kbtu.renthouse.repository.entity.address.QAddressEntity address;

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    public final QUser author;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> floor = createNumber("floor", Integer.class);

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final StringPath id = createString("id");

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isChecked = createBoolean("isChecked");

    public final NumberPath<Integer> numberOfResidents = createNumber("numberOfResidents", Integer.class);

    public final NumberPath<Integer> numberOfRooms = createNumber("numberOfRooms", Integer.class);

    public final SetPath<Photo, QPhoto> photos = this.<Photo, QPhoto>createSet("photos", Photo.class, QPhoto.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final EnumPath<TypeOfHouse> typeOfHouse = createEnum("typeOfHouse", TypeOfHouse.class);

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QHouseEntity(String variable) {
        this(HouseEntity.class, forVariable(variable), INITS);
    }

    public QHouseEntity(Path<? extends HouseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHouseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHouseEntity(PathMetadata metadata, PathInits inits) {
        this(HouseEntity.class, metadata, inits);
    }

    public QHouseEntity(Class<? extends HouseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new kz.kbtu.renthouse.repository.entity.address.QAddressEntity(forProperty("address"), inits.get("address")) : null;
        this.author = inits.isInitialized("author") ? new QUser(forProperty("author"), inits.get("author")) : null;
    }

}

