package kz.kbtu.renthouse.mapper;

import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HouseMapper {

    HouseDTO map(HouseEntity houseEntity);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    HouseEntity map(CreateHouseDTO createHouseDTO);

    void map(@MappingTarget HouseEntity houseEntity, UpdateHouseDTO updateHouseDTO);
}
