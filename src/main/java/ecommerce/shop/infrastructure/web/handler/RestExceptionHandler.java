package ecommerce.shop.infrastructure.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ecommerce.shop.domain.exception.BusinessException;
import ecommerce.shop.domain.exception.EntityNotFoundException;

import java.util.HashMap;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    private ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException businessException) {
        final var exception = new ErrorResponse(HttpStatus.BAD_REQUEST, businessException.getMessage(), null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    private ResponseEntity<ErrorResponse> entityNotFoundExceptionHandler(EntityNotFoundException entityNotFoundException) {
        final var exception = new ErrorResponse(HttpStatus.NOT_FOUND, entityNotFoundException.getMessage(), null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    private ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        final var errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        final var exception = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(value = {Exception.class})
    private ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception) {
        final var errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
