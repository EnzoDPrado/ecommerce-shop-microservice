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
        this.validateProductInput(input);

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

    private void validateProductInput(CreateProductInputDTO input) {
        // Validação de nome
        if (input.name() == null || input.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (input.name().length() < 3 || input.name().length() > 100) {
            throw new IllegalArgumentException("Product name must be between 3 and 100 characters");
        }

        // Validação de descrição
        if (input.description() != null && input.description().length() > 255) {
            throw new IllegalArgumentException("Product description cannot exceed 255 characters");
        }

        // Validação de preço
        if (input.price() == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }

        if (input.price().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
    }
}

