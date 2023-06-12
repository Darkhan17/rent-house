package kz.kbtu.renthouse.domain.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String phoneNumber;
    private String photo;
    private List<String> hobbyIds;
    private String description;
    private String cityId;
}
