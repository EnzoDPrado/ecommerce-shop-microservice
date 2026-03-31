package ecommerce.shop.infrastructure.web.handler;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;

    private String reason;

    private Object details = null;
}
