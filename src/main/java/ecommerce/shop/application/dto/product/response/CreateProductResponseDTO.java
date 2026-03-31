package ecommerce.shop.application.dto.product.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateProductResponseDTO(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    LocalDateTime createdAt
) {
}

