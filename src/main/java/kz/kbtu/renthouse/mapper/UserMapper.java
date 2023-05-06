package kz.kbtu.renthouse.mapper;

import kz.kbtu.renthouse.domain.dto.user.CreateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UpdateUserDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "email", expression = "java(createUserDTO.getEmail().toLowerCase())")
    User map(CreateUserDTO createUserDTO);

    UserDTO map(User user);

    List<UserDTO> map(List<User> users);

    void mapNonNullValues(@MappingTarget User user, UpdateUserDTO updateUserDTO);
}
