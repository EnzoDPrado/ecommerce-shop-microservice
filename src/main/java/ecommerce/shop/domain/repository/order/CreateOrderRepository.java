package ecommerce.shop.domain.repository.order;

import ecommerce.shop.domain.entity.Order;

public interface CreateOrderRepository {
    Order create(Order order);
}

