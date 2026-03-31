package ecommerce.shop.application.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @Email
        String email,

        @NotBlank
        String password
) {
}
