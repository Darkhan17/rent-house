package kz.kbtu.renthouse.domain.dto.auth;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull
    @NotEmpty
    private String email;

    @NotEmpty
    @NotNull
    private String password;
}
