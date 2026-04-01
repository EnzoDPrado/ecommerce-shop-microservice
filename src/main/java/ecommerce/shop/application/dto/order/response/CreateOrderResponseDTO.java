package ecommerce.shop.application.dto.order.response;

import java.util.UUID;

public record CreateOrderResponseDTO(
    UUID orderId
) {
}

