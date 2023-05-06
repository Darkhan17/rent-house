package kz.kbtu.renthouse.mapper;

import kz.kbtu.renthouse.domain.dto.address.AddressDTO;
import kz.kbtu.renthouse.domain.dto.address.CreateAddressDTO;
import kz.kbtu.renthouse.repository.entity.address.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    AddressEntity map(CreateAddressDTO createAddressDTO);

    AddressDTO map(AddressEntity address);
}
