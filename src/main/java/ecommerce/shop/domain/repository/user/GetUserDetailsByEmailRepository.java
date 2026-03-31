package ecommerce.shop.domain.repository.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface GetUserDetailsByEmailRepository {
    UserDetails getUserDetailsByEmail(String email);
}
