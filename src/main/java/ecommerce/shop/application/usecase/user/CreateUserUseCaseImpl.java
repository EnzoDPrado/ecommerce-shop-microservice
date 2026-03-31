package ecommerce.shop.application.usecase.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ecommerce.shop.application.dto.user.request.CreateUserInputDTO;
import ecommerce.shop.domain.entity.User;
import ecommerce.shop.domain.enums.UserRole;
import ecommerce.shop.domain.repository.user.CreateUserRepository;
import ecommerce.shop.domain.repository.user.ExistsUserByEmailRepository;
import ecommerce.shop.domain.usecase.user.CreateUserUseCase;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final ExistsUserByEmailRepository existsUserByEmailRepository;
    private final CreateUserRepository createUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void execute(CreateUserInputDTO input) {
        this.validateUniqueEmail(input.email());

        final var user = this.createUser(input);

        this.createUserRepository.create(user);
    }

    private User createUser(CreateUserInputDTO input) {
        return new User()
                .withName(input.name())
                .withEmail(input.email())
                .withPassword(bCryptPasswordEncoder.encode(input.password()))
                .withRole(UserRole.USER);
    }

    private void validateUniqueEmail(String email) {
        if (existsUserByEmailRepository.existsByEmail(email)) {
            throw new Error("Email already exists"); // todo - create a error handler
        }
    }
}
