package kz.kbtu.renthouse.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHouseDTO {
    private TypeOfHouse typeOfHouse;
    private String description;
    private BigDecimal price;
}
