package ecommerce.shop.infrastructure.web.specs;

import ecommerce.shop.application.dto.product.request.CreateProductInputDTO;
import ecommerce.shop.application.dto.product.request.UpdateProductInputDTO;
import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;
import ecommerce.shop.application.dto.product.response.ListProductsResponseDTO;
import ecommerce.shop.application.dto.product.response.UpdateProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "2. Products", description = "Product management operations")
public interface ProductControllerSpecs {

    @Operation(summary = "Create Product", description = "Create a new product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created successfully")
    })
    @PostMapping
    @SecurityRequirement(name = "jwt")
    ResponseEntity<CreateProductResponseDTO> createProduct(@RequestBody @Valid CreateProductInputDTO input);

    @Operation(summary = "List Products", description = "List products with optional name filter and pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products listed successfully")
    })
    @SecurityRequirement(name = "jwt")
    @GetMapping
    ResponseEntity<ListProductsResponseDTO> listProducts(
            @Parameter(description = "Product name filter") @RequestParam(required = false) String name,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer pageSize);

    @Operation(summary = "Update Product", description = "Update an existing product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated successfully")
    })
    @SecurityRequirement(name = "jwt")
    @PutMapping("/{id}")
    ResponseEntity<UpdateProductResponseDTO> updateProduct(@PathVariable UUID id, @RequestBody @Valid UpdateProductInputDTO input);

    @Operation(summary = "Delete Product", description = "Delete an existing product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    })
    @SecurityRequirement(name = "jwt")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable UUID id);
}

