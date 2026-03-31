package ecommerce.shop.infrastructure.web.controller;

import ecommerce.shop.application.dto.product.request.CreateProductInputDTO;
import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;
import ecommerce.shop.domain.usecase.product.CreateProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    @PostMapping
    public ResponseEntity<CreateProductResponseDTO> createProduct(
            @RequestBody @Valid CreateProductInputDTO input) {
        
        final var response = this.createProductUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

