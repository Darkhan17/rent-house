package kz.kbtu.renthouse.repository.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name = "saved-houses")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SavedHouse {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HouseEntity house;

    public SavedHouse(User user, HouseEntity house) {
        this.user = user;
        this.house = house;
    }
}
