package ecommerce.shop.domain.usecase.user;

import ecommerce.shop.application.dto.user.request.CreateUserInputDTO;

public interface CreateUserUseCase {
    void execute(CreateUserInputDTO input);
}
