package kz.kbtu.renthouse.domain.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kz.kbtu.renthouse.repository.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    @NumberFormat
    private String phoneNumber;

    private Gender gender;

    private String photo;
}
