package ecommerce.shop.infrastructure.web.specs;

import ecommerce.shop.application.dto.auth.request.AuthenticationDTO;
import ecommerce.shop.application.dto.auth.response.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "1. Auth", description = "Authentication operations")
public interface AuthControllerSpecs {

    @Operation(summary = "Login", description = "Authenticate user and return access token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User authenticated successfully")
    })
    @PostMapping
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO input);
}

