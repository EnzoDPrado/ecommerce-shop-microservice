package ecommerce.shop.application.usecase.product;

import ecommerce.shop.application.dto.product.request.ListProductsInputDTO;
import ecommerce.shop.application.dto.product.response.ListProductsResponseDTO;
import ecommerce.shop.application.dto.product.response.ListProductsResponseDTO.PaginationDTO;
import ecommerce.shop.domain.repository.product.ListProductsRepository;
import ecommerce.shop.domain.usecase.product.ListProductsUseCase;
import ecommerce.shop.infrastructure.persistence.mapper.ProductStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListProductsUseCaseImpl implements ListProductsUseCase {

    private final ListProductsRepository listProductsRepository;
    private final ProductStructMapper productStructMapper;

    @Transactional(readOnly = true)
    public ListProductsResponseDTO execute(ListProductsInputDTO input) {
        final var pageable = this.buildPageable(input);
        final var productsPage = this.listProductsRepository.listByNameFilter(
            input.name(),
            pageable
        );

        final var products = productsPage.getContent()
            .stream()
            .map(this.productStructMapper::toProductDTO)
            .collect(Collectors.toList());

        final var pagination = new PaginationDTO(
            input.page(),
            input.pageSize(),
            productsPage.getTotalElements(),
            productsPage.getTotalPages()
        );

        return new ListProductsResponseDTO(products, pagination);
    }

    private Pageable buildPageable(ListProductsInputDTO input) {
        return PageRequest.of(input.page(), input.pageSize());
    }
}

