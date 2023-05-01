package kz.kbtu.renthouse.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "house")
    private Set<Photo> photos;

    private boolean active;
}
