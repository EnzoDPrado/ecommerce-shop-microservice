package ecommerce.shop.infrastructure.persistence.mapper;

import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;
import ecommerce.shop.application.dto.product.response.ListProductsResponseDTO.ProductDTO;
import ecommerce.shop.application.dto.product.response.UpdateProductResponseDTO;
import ecommerce.shop.domain.entity.Product;
import ecommerce.shop.infrastructure.persistence.entity.ProductJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStructMapper {

    ProductJpaEntity toProductJpaEntity(Product product);

    Product toDomainProduct(ProductJpaEntity productJpaEntity);

    CreateProductResponseDTO toCreateProductResponseDTO(Product product);

    ProductDTO toProductDTO(Product product);

    UpdateProductResponseDTO toUpdateProductResponseDTO(Product product);
}

