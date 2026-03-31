package ecommerce.shop.domain.repository.product;

import ecommerce.shop.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListProductsRepository {
    Page<Product> listByNameFilter(String name, Pageable pageable);
}

