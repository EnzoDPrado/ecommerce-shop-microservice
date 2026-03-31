package ecommerce.shop.infrastructure.web.controller;

import ecommerce.shop.application.dto.auth.request.AuthenticationDTO;
import ecommerce.shop.domain.usecase.auth.LoginUseCase;
import ecommerce.shop.infrastructure.web.specs.AuthControllerSpecs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerSpecs {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO input){
        final var response = this.loginUseCase.execute(input);

        return ResponseEntity.ok(response);
    }
}
