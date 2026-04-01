package ecommerce.shop.infrastructure.web.specs;

import ecommerce.shop.application.dto.order.request.CreateOrderInputDTO;
import ecommerce.shop.application.dto.order.response.CreateOrderResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "4. Orders", description = "Order management operations")
public interface OrderControllerSpecs {

    @Operation(summary = "Create Order", description = "Create a new order for the authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order created successfully")
    })
    @PostMapping
    @SecurityRequirement(name = "jwt")
    ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody @Valid CreateOrderInputDTO input, Authentication authentication);
}

