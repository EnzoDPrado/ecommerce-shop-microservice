package ecommerce.shop.infrastructure.persistence.repository.user;

import ecommerce.shop.domain.entity.User;
import ecommerce.shop.domain.exception.EntityNotFoundException;
import ecommerce.shop.domain.repository.user.CreateUserRepository;
import ecommerce.shop.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.security.core.userdetails.UserDetails;
import ecommerce.shop.domain.repository.user.ExistsUserByEmailRepository;
import ecommerce.shop.domain.repository.user.GetUserDetailsByEmailRepository;
import ecommerce.shop.infrastructure.persistence.mapper.UserStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements ExistsUserByEmailRepository, CreateUserRepository, GetUserDetailsByEmailRepository {
    private final UserJpaRepository userRepository;
    private final UserStructMapper userStructMapper;

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User create(User user) {
        var userJpaEntity = userStructMapper.toUserJpaEntity(user);
        var savedEntity = this.userRepository.save(userJpaEntity);
        return userStructMapper.toDomainUser(savedEntity);
    }

    public Optional<UserDetails> getUserDetailsByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .map(userJpaEntity -> (UserDetails) userJpaEntity);
    }
}
