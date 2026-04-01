package ecommerce.shop.infrastructure.persistence.mapper;

import ecommerce.shop.domain.entity.Order;
import ecommerce.shop.infrastructure.persistence.entity.OrderJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStructMapper {

    OrderJpaEntity toOrderJpaEntity(Order order);

    Order toDomainOrder(OrderJpaEntity orderJpaEntity);
}

