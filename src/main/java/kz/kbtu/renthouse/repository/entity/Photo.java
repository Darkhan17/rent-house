package kz.kbtu.renthouse.repository.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity(name = "photos")
@Getter
@Setter
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String link;

    private LocalDate localDate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private HouseEntity house;

    public Photo(String link, HouseEntity house) {
        this.link = link;
        this.localDate = LocalDate.now();
        this.house = house;
    }
}
