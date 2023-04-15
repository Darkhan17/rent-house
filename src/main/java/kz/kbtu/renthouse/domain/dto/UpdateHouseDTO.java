package kz.kbtu.renthouse.domain.dto;

import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHouseDTO {
    private TypeOfHouse typeOfHouse;
    private String description;
    private BigDecimal price;
}
