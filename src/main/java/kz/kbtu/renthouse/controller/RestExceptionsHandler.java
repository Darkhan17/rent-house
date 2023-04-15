package kz.kbtu.renthouse.controller;

import jakarta.servlet.http.HttpServletRequest;
import kz.kbtu.renthouse.domain.dto.exception.ErrorDetails;
import kz.kbtu.renthouse.domain.dto.exception.RentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackages = {"kz.kbtu.renthouse.controller"})
@Slf4j
public class RestExceptionsHandler {

    /**
     * Обработка всех неизвестных исключений
     *
     * @param request
     * @param ex
     * @return
     */

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDetails handleUnknown(HttpServletRequest request, Exception ex) {
        log.warn("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage(), ex);
        return ErrorDetails.builder().message(ex.getMessage()).url(request.getRequestURI()).build();
    }


    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RentException.class)
    public ErrorDetails handleCustom(HttpServletRequest request, RentException ex) {
        log.warn("BaseException.class: {}", ex.toString());
        log.warn("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage(), ex);
        return ErrorDetails.builder().errorCode(ex.getExceptionCode()).message(ex.getMessage())
                .url(request.getRequestURI()).build();
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorDetails handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
        return ErrorDetails.builder()
                .errorCode(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .url(request.getRequestURI())
                .build();
    }
}



