package ecommerce.shop.domain.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public boolean isActive() {
        return this.deletedAt == null;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}

