package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 273090704L;

    public static final QUser user = new QUser("user");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final SetPath<Hobby, QHobby> hobbies = this.<Hobby, QHobby>createSet("hobbies", Hobby.class, QHobby.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath photo = createString("photo");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final SetPath<SocialMediaProfile, QSocialMediaProfile> socialMediaProfiles = this.<SocialMediaProfile, QSocialMediaProfile>createSet("socialMediaProfiles", SocialMediaProfile.class, QSocialMediaProfile.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

