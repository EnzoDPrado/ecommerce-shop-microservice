package ecommerce.shop.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ecommerce.shop.domain.enums.UserRole;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "users")
@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isEnabled() {
        return Objects.isNull(this.deletedAt);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

