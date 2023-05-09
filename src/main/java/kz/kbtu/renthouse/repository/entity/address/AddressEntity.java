package kz.kbtu.renthouse.repository.entity.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import kz.kbtu.renthouse.repository.entity.HouseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name = "addresses")
@Entity
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    private City city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;


    @JsonIgnore
    @JsonIgnoreProperties("house")
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HouseEntity house;
}
