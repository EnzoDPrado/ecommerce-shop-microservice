package ecommerce.shop.domain.entity;

import ecommerce.shop.domain.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private UUID id;
    private UUID userId;
    private UUID productId;
    private Integer quantity;
    private OrderStatus status;
    private LocalDateTime createdAt;
}

