package ecommerce.shop.infrastructure.persistence.repository.product;

import ecommerce.shop.domain.entity.Product;
import ecommerce.shop.domain.repository.product.CreateProductRepository;
import ecommerce.shop.domain.repository.product.FindProductByIdRepository;
import ecommerce.shop.domain.repository.product.ListProductsRepository;
import ecommerce.shop.domain.repository.product.UpdateProductRepository;
import ecommerce.shop.infrastructure.persistence.mapper.ProductStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements CreateProductRepository, ListProductsRepository, UpdateProductRepository, FindProductByIdRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductStructMapper productStructMapper;

    @Override
    public Product create(Product product) {
        var productJpaEntity = productStructMapper.toProductJpaEntity(product);
        var savedEntity = this.productJpaRepository.save(productJpaEntity);
        return productStructMapper.toDomainProduct(savedEntity);
    }

    @Override
    public Page<Product> listByNameFilter(String name, Pageable pageable) {
        var productsPage = this.productJpaRepository.findAllActiveByName(name, pageable);
        return productsPage.map(this.productStructMapper::toDomainProduct);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return this.productJpaRepository.findByIdAndActive(id)
                .map(this.productStructMapper::toDomainProduct);
    }

    @Override
    public Product update(Product product) {
        var productJpaEntity = productStructMapper.toProductJpaEntity(product);
        var updatedEntity = this.productJpaRepository.save(productJpaEntity);
        return productStructMapper.toDomainProduct(updatedEntity);
    }
}

