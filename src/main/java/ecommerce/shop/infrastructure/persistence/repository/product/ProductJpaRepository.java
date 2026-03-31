package ecommerce.shop.infrastructure.persistence.repository.product;

import ecommerce.shop.infrastructure.persistence.entity.ProductJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, UUID> {
    
    @Query("SELECT p FROM products p WHERE p.deletedAt IS NULL AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<ProductJpaEntity> findAllActiveByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM products p WHERE p.id = :id AND p.deletedAt IS NULL")
    Optional<ProductJpaEntity> findByIdAndActive(@Param("id") UUID id);
}

