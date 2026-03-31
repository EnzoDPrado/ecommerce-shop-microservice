package ecommerce.shop.application.usecase.product;

import ecommerce.shop.application.dto.product.request.UpdateProductInputDTO;
import ecommerce.shop.application.dto.product.response.UpdateProductResponseDTO;
import ecommerce.shop.domain.entity.Product;
import ecommerce.shop.domain.exception.EntityNotFoundException;
import ecommerce.shop.domain.repository.product.FindProductByIdRepository;
import ecommerce.shop.domain.repository.product.UpdateProductRepository;
import ecommerce.shop.domain.usecase.product.UpdateProductUseCase;
import ecommerce.shop.infrastructure.persistence.mapper.ProductStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final UpdateProductRepository updateProductRepository;
    private final FindProductByIdRepository findProductByIdRepository;
    private final ProductStructMapper productStructMapper;

    @Transactional
    public UpdateProductResponseDTO execute(UpdateProductInputDTO input) {
        final var existingProduct = this.findProductByIdRepository.findById(input.id())
            .orElseThrow(() -> new EntityNotFoundException( Product.class.getName(), input.id().toString()));

        final var updatedProduct = this.updateProduct(existingProduct, input);

        final var savedProduct = this.updateProductRepository.update(updatedProduct);

        return this.productStructMapper.toUpdateProductResponseDTO(savedProduct);
    }

    private Product updateProduct(Product existingProduct, UpdateProductInputDTO input) {
        return existingProduct
            .withName(input.name() != null ? input.name().trim() : existingProduct.getName())
            .withDescription(input.description() != null ? input.description().trim() : existingProduct.getDescription())
            .withPrice(input.price() != null ? input.price() : existingProduct.getPrice())
            .withUpdatedAt(LocalDateTime.now());
    }
}

