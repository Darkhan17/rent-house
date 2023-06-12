package kz.kbtu.renthouse.mapper;


import kz.kbtu.renthouse.domain.dto.rommates.SavedRoommateDTO;
import kz.kbtu.renthouse.repository.entity.SavedRoommates;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {UserMapper.class, AddressMapper.class} )
public interface RoommateMapper {
    SavedRoommateDTO map(SavedRoommates savedRoommates);

    List<SavedRoommateDTO> map(List<SavedRoommates> savedRoommatesList);
}
