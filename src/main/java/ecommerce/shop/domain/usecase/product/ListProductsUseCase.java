package ecommerce.shop.domain.usecase.product;

import ecommerce.shop.application.dto.product.request.ListProductsInputDTO;
import ecommerce.shop.application.dto.product.response.ListProductsResponseDTO;

public interface ListProductsUseCase {
    ListProductsResponseDTO execute(ListProductsInputDTO input);
}

