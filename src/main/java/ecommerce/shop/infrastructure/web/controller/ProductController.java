package ecommerce.shop.infrastructure.web.controller;

import ecommerce.shop.application.dto.product.request.CreateProductInputDTO;
import ecommerce.shop.application.dto.product.request.ListProductsInputDTO;
import ecommerce.shop.application.dto.product.request.UpdateProductInputDTO;
import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;
import ecommerce.shop.application.dto.product.response.ListProductsResponseDTO;
import ecommerce.shop.application.dto.product.response.UpdateProductResponseDTO;
import ecommerce.shop.domain.usecase.product.CreateProductUseCase;
import ecommerce.shop.domain.usecase.product.DeleteProductUseCase;
import ecommerce.shop.domain.usecase.product.ListProductsUseCase;
import ecommerce.shop.domain.usecase.product.UpdateProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

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

    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductResponseDTO> updateProduct(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProductInputDTO input) {

        final var inputWithId = new UpdateProductInputDTO(id, input.name(), input.description(), input.price());
        final var response = this.updateProductUseCase.execute(inputWithId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable UUID id) {

        this.deleteProductUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}

