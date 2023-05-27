package kz.kbtu.renthouse.domain.dto;

import jakarta.persistence.Column;
import kz.kbtu.renthouse.domain.dto.address.AddressDTO;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.repository.entity.Photo;
import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {
    private String id;
    private TypeOfHouse typeOfHouse;
    private UserDTO author;
    private String description;
    private BigDecimal price;
    private Integer numberOfResidents;
    private LocalDate createdAt;
    private AddressDTO address;
    private boolean isActive;
    private boolean isChecked;
    private int area;
    private int numberOfRooms;
    private int floor;
    private Set<Photo> photos;
}
