package ecommerce.shop.domain.usecase.order;

import ecommerce.shop.domain.entity.Order;

import java.util.UUID;

public interface CreateOrderUseCase {
    Order execute(UUID productId, Integer quantity, String userEmail);
}



