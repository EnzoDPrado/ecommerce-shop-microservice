package ecommerce.shop.infrastructure.config;

import ecommerce.shop.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ecommerce.shop.infrastructure.security.SecurityFilter;
import ecommerce.shop.infrastructure.security.CustomAuthenticationEntryPoint;
import ecommerce.shop.infrastructure.security.CustomAccessDeniedHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] SWAGGER_RESOURCES = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/docs/**"
    };

    private static final String[] PUBLIC_POST_ENDPOINTS = {
            "/auth",
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
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, ADMIN_POST_ENDPOINTS).hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, ADMIN_PUT_ENDPOINTS).hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, ADMIN_DELETE_ENDPOINTS).hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers(SWAGGER_RESOURCES).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authProvider);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
