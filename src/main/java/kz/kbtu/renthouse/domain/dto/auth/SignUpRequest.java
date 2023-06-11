package kz.kbtu.renthouse.domain.dto.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kz.kbtu.renthouse.repository.entity.Gender;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String photo;

    @NotBlank
    @NotNull
    private String phoneNumber;

    @NotNull
    private Gender gender;

    private String cityId;
}
