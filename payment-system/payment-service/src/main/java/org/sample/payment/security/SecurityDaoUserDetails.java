package org.sample.payment.security;

import lombok.RequiredArgsConstructor;
import org.sample.payment.dao.entity.UserEntity;
import org.sample.payment.dao.repo.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.Optional;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
@RequiredArgsConstructor
public class SecurityDaoUserDetails implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isPresent()) {
            return new SecurityUserDetails(userEntity.get().getUsername(), userEntity.get().getPassword());
        } else {
            return null;
        }
    }
}
