package br.com.deusaprincess.serviceimpl;

import br.com.deusaprincess.exception.NotFoundException;
import br.com.deusaprincess.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        br.com.deusaprincess.domain.User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado: " + username));

        return new User(user.getEmail(), user.getPassword(), user.isActive(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
    }
}
