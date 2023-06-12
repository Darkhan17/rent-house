package kz.kbtu.renthouse.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kz.kbtu.renthouse.domain.dto.rommates.SavedRoommateDTO;
import kz.kbtu.renthouse.repository.entity.SavedRoommates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T00:29:20+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class RoommateMapperImpl implements RoommateMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SavedRoommateDTO map(SavedRoommates savedRoommates) {
        if ( savedRoommates == null ) {
            return null;
        }

        SavedRoommateDTO savedRoommateDTO = new SavedRoommateDTO();

        savedRoommateDTO.setId( savedRoommates.getId() );
        savedRoommateDTO.setSavedUsers( userMapper.map( savedRoommates.getSavedUsers() ) );
        savedRoommateDTO.setAddedAt( savedRoommates.getAddedAt() );

        return savedRoommateDTO;
    }

    @Override
    public List<SavedRoommateDTO> map(List<SavedRoommates> savedRoommatesList) {
        if ( savedRoommatesList == null ) {
            return null;
        }

        List<SavedRoommateDTO> list = new ArrayList<SavedRoommateDTO>( savedRoommatesList.size() );
        for ( SavedRoommates savedRoommates : savedRoommatesList ) {
            list.add( map( savedRoommates ) );
        }

        return list;
    }
}
