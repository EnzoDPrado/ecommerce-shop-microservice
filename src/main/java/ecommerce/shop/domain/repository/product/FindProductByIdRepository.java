package ecommerce.shop.domain.repository.product;

import ecommerce.shop.domain.entity.Product;

import java.util.Optional;

public interface FindProductByIdRepository {
    Optional<Product> findById(java.util.UUID id);
}

