package ecommerce.shop.infrastructure.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ecommerce.shop.domain.exception.BusinessException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    private ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException businessException) {
        final var exception = new ErrorResponse(HttpStatus.BAD_REQUEST, businessException.getMessage(), null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
}
