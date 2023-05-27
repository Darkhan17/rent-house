package kz.kbtu.renthouse.domain.dto;

import kz.kbtu.renthouse.domain.dto.address.CreateAddressDTO;
import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import kz.kbtu.renthouse.repository.entity.address.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHouseDTO {
    private TypeOfHouse typeOfHouse;
    private String description;
    private BigDecimal price;
    private Set<String> photos = new HashSet<>();
    private String authorId;
    private CreateAddressDTO address;
    private int numberOfResidents;
    private int area;
    private int numberOfRooms;
    private int floor;
}
