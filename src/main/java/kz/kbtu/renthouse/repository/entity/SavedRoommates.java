package kz.kbtu.renthouse.repository.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Table(name = "saved-rommates")
@Entity
@Getter
@Setter
public class SavedRoommates {

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
    private User savedUsers;

    private LocalDate addedAt;

    public SavedRoommates(User user, User savedUsers) {
        this.user = user;
        this.savedUsers = savedUsers;
        this.addedAt = LocalDate.now();
    }

    public SavedRoommates() {

    }
}
