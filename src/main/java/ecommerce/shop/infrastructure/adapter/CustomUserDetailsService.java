package ecommerce.shop.infrastructure.adapter;

import ecommerce.shop.domain.repository.user.GetUserDetailsByEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final GetUserDetailsByEmailRepository getUserDetailsByEmailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserDetailsByEmailRepository.getUserDetailsByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas"));
    }
}
