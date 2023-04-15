package kz.kbtu.renthouse.domain.dto.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorDetails {
    int errorCode;
    String message;
    String url;
}
