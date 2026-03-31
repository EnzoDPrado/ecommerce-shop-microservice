package ecommerce.shop.domain.usecase.product;

import ecommerce.shop.application.dto.product.request.CreateProductInputDTO;
import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;

public interface CreateProductUseCase {
    CreateProductResponseDTO execute(CreateProductInputDTO input);
}

