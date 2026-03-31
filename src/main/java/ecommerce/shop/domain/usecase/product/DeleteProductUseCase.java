package ecommerce.shop.domain.usecase.product;

import java.util.UUID;

public interface DeleteProductUseCase {
    void execute(UUID id);
}

