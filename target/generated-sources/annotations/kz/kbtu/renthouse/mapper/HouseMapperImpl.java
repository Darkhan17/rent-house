package kz.kbtu.renthouse.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kz.kbtu.renthouse.domain.dto.CreateHouseDTO;
import kz.kbtu.renthouse.domain.dto.HouseDTO;
import kz.kbtu.renthouse.domain.dto.UpdateHouseDTO;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import kz.kbtu.renthouse.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-08T23:38:17+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class HouseMapperImpl implements HouseMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public HouseDTO map(HouseEntity houseEntity) {
        if ( houseEntity == null ) {
            return null;
        }

        HouseDTO houseDTO = new HouseDTO();

        houseDTO.setId( houseEntity.getId() );
        houseDTO.setTypeOfHouse( houseEntity.getTypeOfHouse() );
        houseDTO.setAuthor( userMapper.map( houseEntity.getAuthor() ) );
        houseDTO.setDescription( houseEntity.getDescription() );
        houseDTO.setCreatedAt( houseEntity.getCreatedAt() );
        houseDTO.setAddress( addressMapper.map( houseEntity.getAddress() ) );
        houseDTO.setActive( houseEntity.isActive() );
        houseDTO.setChecked( houseEntity.isChecked() );

        return houseDTO;
    }

    @Override
    public List<HouseDTO> map(List<HouseEntity> houseEntity) {
        if ( houseEntity == null ) {
            return null;
        }

        List<HouseDTO> list = new ArrayList<HouseDTO>( houseEntity.size() );
        for ( HouseEntity houseEntity1 : houseEntity ) {
            list.add( map( houseEntity1 ) );
        }

        return list;
    }

    @Override
    public HouseEntity map(CreateHouseDTO createHouseDTO, User user) {
        if ( createHouseDTO == null && user == null ) {
            return null;
        }

        HouseEntity houseEntity = new HouseEntity();

        if ( createHouseDTO != null ) {
            houseEntity.setTypeOfHouse( createHouseDTO.getTypeOfHouse() );
            houseEntity.setAddress( addressMapper.map( createHouseDTO.getAddress() ) );
            houseEntity.setDescription( createHouseDTO.getDescription() );
            houseEntity.setPrice( createHouseDTO.getPrice() );
        }
        if ( user != null ) {
            houseEntity.setAuthor( user );
            houseEntity.setId( user.getId() );
            houseEntity.setActive( user.isActive() );
        }
        houseEntity.setCreatedAt( java.time.LocalDate.now() );
        houseEntity.setPhotos( new java.util.HashSet<>() );

        return houseEntity;
    }

    @Override
    public void map(HouseEntity houseEntity, UpdateHouseDTO updateHouseDTO) {
        if ( updateHouseDTO == null ) {
            return;
        }

        if ( updateHouseDTO.getTypeOfHouse() != null ) {
            houseEntity.setTypeOfHouse( updateHouseDTO.getTypeOfHouse() );
        }
        if ( updateHouseDTO.getDescription() != null ) {
            houseEntity.setDescription( updateHouseDTO.getDescription() );
        }
        if ( updateHouseDTO.getPrice() != null ) {
            houseEntity.setPrice( updateHouseDTO.getPrice() );
        }
    }
}
