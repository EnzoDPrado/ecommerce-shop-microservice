package ecommerce.shop.application.usecase.product;

import ecommerce.shop.domain.entity.Product;
import ecommerce.shop.domain.exception.BusinessException;
import ecommerce.shop.domain.exception.EntityNotFoundException;
import ecommerce.shop.domain.repository.product.FindProductByIdRepository;
import ecommerce.shop.domain.repository.product.UpdateProductRepository;
import ecommerce.shop.domain.usecase.product.DeleteProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final UpdateProductRepository updateProductRepository;
    private final FindProductByIdRepository findProductByIdRepository;

    @Transactional
    public void execute(UUID id) {
        final var product = this.findProductByIdRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class.getName(), id.toString()));

        this.validateProductNotDeleted(product);

        product.delete();

        this.updateProductRepository.update(product);
    }

    private void validateProductNotDeleted(Product product) {
        if (!product.isActive()) {
            throw new BusinessException("O produto já foi deletado");
        }
    }
}

