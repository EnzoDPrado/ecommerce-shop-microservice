package ecommerce.shop.domain.repository.product;

import ecommerce.shop.domain.entity.Product;

import java.util.Optional;

public interface UpdateProductRepository {
    Product update(Product product);
}

