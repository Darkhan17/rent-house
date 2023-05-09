package kz.kbtu.renthouse.domain.dto.address;

import kz.kbtu.renthouse.repository.entity.address.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String id;
    private String postalCode;
    private String name;
    private String description;
    private City city;
}
