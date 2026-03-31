package ecommerce.shop.domain.repository.user;

public interface ExistsUserByEmailRepository {
    boolean existsByEmail(String email);
}
