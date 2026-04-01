package ecommerce.shop.infrastructure.web.specs;

import ecommerce.shop.application.dto.user.request.CreateUserInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "3. Users", description = "User management operations")
public interface UserControllerSpecs {

    @Operation(summary = "Create User", description = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully")
    })
    @PostMapping
    ResponseEntity<Void> createUser(@RequestBody CreateUserInputDTO input);
}

