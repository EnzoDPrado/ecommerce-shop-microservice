package ecommerce.shop.application.dto.product.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductInputDTO(
    @NotNull(message = "Product ID cannot be null")
    UUID id,

    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    String name,

    @Size(max = 255, message = "Product description cannot exceed 255 characters")
    String description,

    @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
    BigDecimal price
) {
}

