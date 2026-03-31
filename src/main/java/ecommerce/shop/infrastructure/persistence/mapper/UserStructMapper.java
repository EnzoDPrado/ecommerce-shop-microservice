package ecommerce.shop.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import ecommerce.shop.application.dto.user.request.CreateUserInputDTO;
import ecommerce.shop.domain.entity.User;
import ecommerce.shop.infrastructure.persistence.entity.UserJpaEntity;

@Mapper(componentModel = "spring")
public interface UserStructMapper {
    User toUserEntity(CreateUserInputDTO createUserInputDTO);
    
    UserJpaEntity toUserJpaEntity(User user);
    
    User toDomainUser(UserJpaEntity userJpaEntity);
}
