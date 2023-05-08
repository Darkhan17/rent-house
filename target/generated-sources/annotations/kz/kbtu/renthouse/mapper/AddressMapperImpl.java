package kz.kbtu.renthouse.mapper;

import javax.annotation.processing.Generated;
import kz.kbtu.renthouse.domain.dto.address.AddressDTO;
import kz.kbtu.renthouse.domain.dto.address.CreateAddressDTO;
import kz.kbtu.renthouse.repository.entity.address.AddressEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-08T23:25:13+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressEntity map(CreateAddressDTO createAddressDTO) {
        if ( createAddressDTO == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setPostalCode( createAddressDTO.getPostalCode() );
        addressEntity.setName( createAddressDTO.getName() );
        addressEntity.setDescription( createAddressDTO.getDescription() );

        return addressEntity;
    }

    @Override
    public AddressDTO map(AddressEntity address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId( address.getId() );
        addressDTO.setPostalCode( address.getPostalCode() );
        addressDTO.setName( address.getName() );
        addressDTO.setDescription( address.getDescription() );

        return addressDTO;
    }
}
