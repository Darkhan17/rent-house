package kz.kbtu.renthouse.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UpdateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.repository.entity.Hobby;
import kz.kbtu.renthouse.repository.entity.SocialMediaProfile;
import kz.kbtu.renthouse.repository.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-14T08:51:08+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(CreateUserDTO createUserDTO) {
        if ( createUserDTO == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( createUserDTO.getPassword() );
        user.setPhoto( createUserDTO.getPhoto() );
        user.setPhoneNumber( createUserDTO.getPhoneNumber() );
        user.setGender( createUserDTO.getGender() );

        user.setCreatedAt( java.time.LocalDate.now() );
        user.setEmail( createUserDTO.getEmail().toLowerCase() );
        user.setActive( true );

        return user;
    }

    @Override
    public UserDTO map(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setCreatedAt( user.getCreatedAt() );
        userDTO.setPhoto( user.getPhoto() );
        Set<Hobby> set = user.getHobbies();
        if ( set != null ) {
            userDTO.setHobbies( new LinkedHashSet<Hobby>( set ) );
        }
        Set<SocialMediaProfile> set1 = user.getSocialMediaProfiles();
        if ( set1 != null ) {
            userDTO.setSocialMediaProfiles( new LinkedHashSet<SocialMediaProfile>( set1 ) );
        }
        userDTO.setDescription( user.getDescription() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        userDTO.setActive( user.isActive() );
        userDTO.setRole( user.getRole() );
        userDTO.setGender( user.getGender() );

        return userDTO;
    }

    @Override
    public List<UserDTO> map(Iterable<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>();
        for ( User user : users ) {
            list.add( map( user ) );
        }

        return list;
    }

    @Override
    public void mapNonNullValues(User user, UpdateUserDTO updateUserDTO) {
        if ( updateUserDTO == null ) {
            return;
        }

        if ( updateUserDTO.getPhoto() != null ) {
            user.setPhoto( updateUserDTO.getPhoto() );
        }
        if ( updateUserDTO.getDescription() != null ) {
            user.setDescription( updateUserDTO.getDescription() );
        }
        if ( updateUserDTO.getPhoneNumber() != null ) {
            user.setPhoneNumber( updateUserDTO.getPhoneNumber() );
        }
    }
}
