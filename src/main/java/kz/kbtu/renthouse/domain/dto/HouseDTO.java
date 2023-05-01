package kz.kbtu.renthouse.domain.dto;

import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {
    private String id;
    private TypeOfHouse typeOfHouse;
    private UserDTO author;
    private String description;
    private LocalDate createdAt;
    private boolean active;
}
