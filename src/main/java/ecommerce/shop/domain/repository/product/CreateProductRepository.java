package ecommerce.shop.domain.repository.product;

import ecommerce.shop.domain.entity.Product;

public interface CreateProductRepository {
    Product create(Product product);
}

