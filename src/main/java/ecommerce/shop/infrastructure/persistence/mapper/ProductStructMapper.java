package ecommerce.shop.infrastructure.persistence.mapper;

import ecommerce.shop.application.dto.product.response.CreateProductResponseDTO;
import ecommerce.shop.domain.entity.Product;
import ecommerce.shop.infrastructure.persistence.entity.ProductJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductStructMapper {

    ProductJpaEntity toProductJpaEntity(Product product);

    Product toDomainProduct(ProductJpaEntity productJpaEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "createdAt", target = "createdAt")
    CreateProductResponseDTO toCreateProductResponseDTO(Product product);
}

