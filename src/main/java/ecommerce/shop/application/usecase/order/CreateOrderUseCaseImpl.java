package ecommerce.shop.application.usecase.order;

import ecommerce.shop.domain.entity.Order;
import ecommerce.shop.domain.enums.OrderStatus;
import ecommerce.shop.domain.exception.EntityNotFoundException;
import ecommerce.shop.domain.repository.order.CreateOrderRepository;
import ecommerce.shop.domain.repository.product.FindProductByIdRepository;
import ecommerce.shop.domain.repository.user.FindUserByEmailRepository;
import ecommerce.shop.domain.usecase.order.CreateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final CreateOrderRepository createOrderRepository;
    private final FindProductByIdRepository findProductByIdRepository;
    private final FindUserByEmailRepository findUserByEmailRepository;

    @Transactional
    public Order execute(UUID productId, Integer quantity, String userEmail) {
        final var user = this.findUserByEmailRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        this.findProductByIdRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        final var order = this.buildOrder(productId, quantity, user.getId());
        return this.createOrderRepository.create(order);
    }

    private Order buildOrder(UUID productId, Integer quantity, UUID userId) {
        return Order.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
    }
}

