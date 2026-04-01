package ecommerce.shop.infrastructure.web.controller;

import ecommerce.shop.application.dto.order.request.CreateOrderInputDTO;
import ecommerce.shop.application.dto.order.response.CreateOrderResponseDTO;
import ecommerce.shop.domain.usecase.order.CreateOrderUseCase;
import ecommerce.shop.infrastructure.web.specs.OrderControllerSpecs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController implements OrderControllerSpecs {

    private final CreateOrderUseCase createOrderUseCase;

    @Override
    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody @Valid CreateOrderInputDTO input, Authentication authentication) {
        final var userEmail = authentication.getName();
        final var order = this.createOrderUseCase.execute(input.productId(), input.quantity(), userEmail);

        return ResponseEntity.status(HttpStatus.OK).body(new CreateOrderResponseDTO(order.getId()));
    }
}

