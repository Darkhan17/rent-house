package kz.kbtu.renthouse.domain.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {
    private String postalCode;
    private String name;
    private String description;
}
