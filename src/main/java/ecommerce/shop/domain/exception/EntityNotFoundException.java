package ecommerce.shop.domain.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String entityName, String identifier) {
        super(String.format("%s com identificador '%s' não encontrado", entityName, identifier));
    }
}

