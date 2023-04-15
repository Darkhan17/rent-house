package kz.kbtu.renthouse.domain.dto;

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
    private String authorId;
    private String description;
    private LocalDate createdAt;
}
