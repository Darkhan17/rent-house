package kz.kbtu.renthouse.domain.dto.rommates;

import jakarta.persistence.ManyToOne;
import kz.kbtu.renthouse.domain.dto.user.UserDTO;
import kz.kbtu.renthouse.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedRoommateDTO {
    private String id;
    private UserDTO savedUsers;
    private LocalDate addedAt;
}
