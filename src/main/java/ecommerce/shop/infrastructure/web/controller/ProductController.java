package ecommerce.shop.infrastructure.web.controller;

import ecommerce.shop.application.dto.product.request.CreateProductInputDTO;
import ecommerce.shop.application.dto.product.request.ListProductsInputDTO;
import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;
import ecommerce.shop.application.dto.product.response.ListProductsResponseDTO;
import ecommerce.shop.domain.usecase.product.CreateProductUseCase;
import ecommerce.shop.domain.usecase.product.ListProductsUseCase;
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
    private final ListProductsUseCase listProductsUseCase;

    @PostMapping
    public ResponseEntity<CreateProductResponseDTO> createProduct(
            @RequestBody @Valid CreateProductInputDTO input) {
        
        final var response = this.createProductUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ListProductsResponseDTO> listProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        
        final var input = new ListProductsInputDTO(name, page, pageSize);
        final var response = this.listProductsUseCase.execute(input);

        return ResponseEntity.ok(response);
    }
}

