package ecommerce.shop.infrastructure.persistence.repository.order;

import ecommerce.shop.domain.entity.Order;
import ecommerce.shop.domain.repository.order.CreateOrderRepository;
import ecommerce.shop.infrastructure.persistence.mapper.OrderStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements CreateOrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderStructMapper orderStructMapper;

    @Override
    public Order create(Order order) {
        var orderJpaEntity = orderStructMapper.toOrderJpaEntity(order);
        var savedEntity = this.orderJpaRepository.save(orderJpaEntity);
        return orderStructMapper.toDomainOrder(savedEntity);
    }
}

