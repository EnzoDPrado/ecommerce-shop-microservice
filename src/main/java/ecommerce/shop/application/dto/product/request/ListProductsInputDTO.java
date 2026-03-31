package ecommerce.shop.application.dto.product.request;

public record ListProductsInputDTO(
    String name,
    Integer page,
    Integer pageSize
) {
    public ListProductsInputDTO {
        if (page == null || page < 0) {
            page = 0;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
    }
}

