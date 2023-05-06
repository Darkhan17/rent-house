package kz.kbtu.renthouse.domain.dto.auth;


import lombok.Data;

@Data
public class CodeRequest {
    private String email;
    private String code;
}
