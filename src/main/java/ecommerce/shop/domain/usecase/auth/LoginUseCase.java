package ecommerce.shop.domain.usecase.auth;

import ecommerce.shop.application.dto.auth.request.AuthenticationDTO;
import ecommerce.shop.application.dto.auth.response.LoginResponseDTO;

public interface LoginUseCase {
    LoginResponseDTO execute(AuthenticationDTO input);
}
