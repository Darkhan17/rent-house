package kz.kbtu.renthouse.domain.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @NotEmpty
    @NotNull
    private String username;

    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    private String photo;
}
