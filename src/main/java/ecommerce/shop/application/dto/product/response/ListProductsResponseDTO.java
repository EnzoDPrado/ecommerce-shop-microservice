package ecommerce.shop.application.dto.product.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ListProductsResponseDTO(
    List<ProductDTO> products,
    PaginationDTO pagination
) {
    public record ProductDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
    }

    public record PaginationDTO(
        Integer page,
        Integer pageSize,
        Long totalElements,
        Integer totalPages
    ) {
    }
}

