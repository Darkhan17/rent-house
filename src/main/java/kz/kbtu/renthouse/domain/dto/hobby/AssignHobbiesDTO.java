package kz.kbtu.renthouse.domain.dto.hobby;

import kz.kbtu.renthouse.repository.entity.Hobby;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignHobbiesDTO {
    Set<Hobby> hobbies;
}
