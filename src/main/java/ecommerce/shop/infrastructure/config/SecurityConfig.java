package ecommerce.shop.infrastructure.config;

import ecommerce.shop.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ecommerce.shop.infrastructure.security.SecurityFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    private static final String[] PUBLIC_POST_ENDPOINTS = {
            "/login",
            "/users"
    };

    private static final String[] ADMIN_POST_ENDPOINTS = {
            "/products"
    };

    private static final String[] ADMIN_PUT_ENDPOINTS = {
            "/products/*"
    };

    private static final String[] ADMIN_DELETE_ENDPOINTS = {
            "/products/*"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, ADMIN_POST_ENDPOINTS).hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, ADMIN_PUT_ENDPOINTS).hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, ADMIN_DELETE_ENDPOINTS).hasAuthority(UserRole.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
