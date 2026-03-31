package ecommerce.shop.domain.usecase.product;

import ecommerce.shop.application.dto.product.request.UpdateProductInputDTO;
import ecommerce.shop.application.dto.product.response.UpdateProductResponseDTO;

public interface UpdateProductUseCase {
    UpdateProductResponseDTO execute(UpdateProductInputDTO input);
}

