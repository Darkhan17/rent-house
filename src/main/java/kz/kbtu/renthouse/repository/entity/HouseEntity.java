package kz.kbtu.renthouse.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "houses")
@Entity
@Getter
@Setter
public class HouseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private TypeOfHouse typeOfHouse;

    @Column(nullable = false)
    private String authorId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private BigDecimal price;
}
