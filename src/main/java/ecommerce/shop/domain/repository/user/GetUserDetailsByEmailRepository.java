package ecommerce.shop.domain.repository.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface GetUserDetailsByEmailRepository {
    Optional<UserDetails> getUserDetailsByEmail(String email);
}
