package ecommerce.shop.domain.repository.user;

import ecommerce.shop.domain.entity.User;

public interface CreateUserRepository {
    User create(User user);
}
