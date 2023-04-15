package kz.kbtu.renthouse.mapper;

import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    User map(CreateUserDTO createUserDTO);

    UserDTO map(User user);
}
