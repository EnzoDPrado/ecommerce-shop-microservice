package ecommerce.shop.application.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserInputDTO(
        @NotBlank
        String name,

        @Email
        String email,

        @NotBlank
        String password
) {
}
