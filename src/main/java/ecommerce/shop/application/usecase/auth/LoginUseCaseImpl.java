package ecommerce.shop.application.usecase.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ecommerce.shop.application.dto.auth.request.AuthenticationDTO;
import ecommerce.shop.application.dto.auth.response.LoginResponseDTO;
import ecommerce.shop.domain.entity.User;
import ecommerce.shop.domain.usecase.auth.LoginUseCase;
import ecommerce.shop.infrastructure.persistence.entity.UserJpaEntity;
import ecommerce.shop.infrastructure.persistence.mapper.UserStructMapper;
import ecommerce.shop.infrastructure.security.TokenService;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserStructMapper userMapper;

    public LoginResponseDTO execute(AuthenticationDTO input) {
        final var userNamePassword = new UsernamePasswordAuthenticationToken(input.email(), input.password());
        final Authentication auth = this.authenticationManager.authenticate(userNamePassword);

        final var userJpaEntity = (UserJpaEntity) auth.getPrincipal();
        final var domainUser = this.userMapper.toDomainUser(userJpaEntity);
        
        final var token = this.tokenService.generateToken(domainUser);

        return new LoginResponseDTO(token);
    }
}
