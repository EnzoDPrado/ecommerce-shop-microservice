package ecommerce.shop.infrastructure.web.controller;

import ecommerce.shop.application.dto.user.request.CreateUserInputDTO;
import ecommerce.shop.domain.usecase.user.CreateUserUseCase;
import ecommerce.shop.infrastructure.web.specs.UserControllerSpecs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserControllerSpecs {
    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid CreateUserInputDTO input) {
        this.createUserUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
