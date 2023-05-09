package kz.kbtu.renthouse.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UpdateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.repository.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-09T16:35:34+0600",
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

        user.setCreatedAt( java.time.LocalDate.now() );
        user.setEmail( createUserDTO.getEmail().toLowerCase() );

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

        return userDTO;
    }

    @Override
    public List<UserDTO> map(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
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
        if ( updateUserDTO.getPhoneNumber() != null ) {
            user.setPhoneNumber( updateUserDTO.getPhoneNumber() );
        }
    }
}
