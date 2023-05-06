package kz.kbtu.renthouse.mapper;

import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.Photo;
import kz.kbtu.renthouse.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {UserMapper.class, AddressMapper.class} )
public interface HouseMapper {

    HouseDTO map(HouseEntity houseEntity);
    List<HouseDTO> map(List<HouseEntity> houseEntity);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "photos", expression = "java(new java.util.HashSet<>())")
    @Mapping(target = "author", source = "user")
    HouseEntity map(CreateHouseDTO createHouseDTO, User user);

    void map(@MappingTarget HouseEntity houseEntity, UpdateHouseDTO updateHouseDTO);
}
