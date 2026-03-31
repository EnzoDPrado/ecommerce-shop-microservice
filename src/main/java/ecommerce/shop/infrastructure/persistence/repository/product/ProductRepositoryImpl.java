package ecommerce.shop.infrastructure.persistence.repository.product;

import ecommerce.shop.domain.entity.Product;
import ecommerce.shop.domain.repository.product.CreateProductRepository;
import ecommerce.shop.infrastructure.persistence.mapper.ProductStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements CreateProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductStructMapper productStructMapper;

    @Override
    public Product create(Product product) {
        var productJpaEntity = productStructMapper.toProductJpaEntity(product);
        var savedEntity = this.productJpaRepository.save(productJpaEntity);
        return productStructMapper.toDomainProduct(savedEntity);
    }
}

