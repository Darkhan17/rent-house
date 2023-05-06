package kz.kbtu.renthouse.domain.dto;

import jakarta.validation.constraints.NotNull;
import kz.kbtu.renthouse.repository.entity.TypeOfHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
public class UpdateHouseDTO {
    private TypeOfHouse typeOfHouse;
    private String description;

    @NotNull
    private BigDecimal price;
    private boolean isActive;


    public TypeOfHouse getTypeOfHouse() {
        return typeOfHouse;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setTypeOfHouse(TypeOfHouse typeOfHouse) {
        this.typeOfHouse = typeOfHouse;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}

