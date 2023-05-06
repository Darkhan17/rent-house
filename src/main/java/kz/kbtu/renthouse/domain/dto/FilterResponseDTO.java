package kz.kbtu.renthouse.domain.dto;


import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterResponseDTO {
    private int priceMinValue;
    private int priceMaxValue;
    private int maxNumberOfResidence;
    private int minNumberOfResidence;
    private List<TypeOfHouse> typeOfHouseList;
}
