package ecommerce.shop.domain.repository.user;

import ecommerce.shop.domain.entity.User;

import java.util.Optional;

public interface FindUserByEmailRepository {
    Optional<User> findByEmail(String email);
}

