package ecommerce.shop.application.usecase.product;

import ecommerce.shop.application.dto.product.request.CreateProductInputDTO;
import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;
import ecommerce.shop.domain.entity.Product;
import ecommerce.shop.domain.repository.product.CreateProductRepository;
import ecommerce.shop.domain.usecase.product.CreateProductUseCase;
import ecommerce.shop.infrastructure.persistence.mapper.ProductStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final CreateProductRepository createProductRepository;
    private final ProductStructMapper productStructMapper;

    @Transactional
    public CreateProductResponseDTO execute(CreateProductInputDTO input) {
        final var product = this.buildProduct(input);

        final var createdProduct = this.createProductRepository.create(product);

        return this.productStructMapper.toCreateProductResponseDTO(createdProduct);
    }

    private Product buildProduct(CreateProductInputDTO input) {
        return Product.builder()
                .id(UUID.randomUUID())
                .name(input.name().trim())
                .description(input.description() != null ? input.description().trim() : null)
                .price(input.price())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

