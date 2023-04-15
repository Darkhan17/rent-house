package kz.kbtu.renthouse.domain.dto.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RentException extends RuntimeException {
    private static final long serialVersionUID = -7056352146473732573L;
    private int exceptionCode;

    public RentException(String message, int exceptionsCode, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionsCode;
    }

    public RentException(String message, int exceptionsCode) {
        super(message);
        this.exceptionCode = exceptionsCode;
    }
}
