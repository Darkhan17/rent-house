package kz.kbtu.renthouse.domain.dto.hobby;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHobbyDTO {
    private String name;
    private String description;
}
