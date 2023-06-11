package kz.kbtu.renthouse.mapper;


import kz.kbtu.renthouse.domain.dto.hobby.CreateHobbyDTO;
import kz.kbtu.renthouse.repository.entity.Hobby;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HobbyMapper {

    Hobby map(CreateHobbyDTO createHobbyDTO);



}
