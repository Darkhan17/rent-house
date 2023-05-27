package kz.kbtu.renthouse.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSocialMediaProfile is a Querydsl query type for SocialMediaProfile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSocialMediaProfile extends EntityPathBase<SocialMediaProfile> {

    private static final long serialVersionUID = 1676892471L;

    public static final QSocialMediaProfile socialMediaProfile = new QSocialMediaProfile("socialMediaProfile");

    public final StringPath id = createString("id");

    public final StringPath link = createString("link");

    public QSocialMediaProfile(String variable) {
        super(SocialMediaProfile.class, forVariable(variable));
    }

    public QSocialMediaProfile(Path<? extends SocialMediaProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSocialMediaProfile(PathMetadata metadata) {
        super(SocialMediaProfile.class, metadata);
    }

}

