package org.sample.payment.security;

import lombok.RequiredArgsConstructor;
import org.sample.payment.dao.repo.IUserRepository;
import org.sample.payment.message.LocaleMessageResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
@RequiredArgsConstructor
public class SecurityDaoUserDetails implements UserDetailsService {
    private final IUserRepository userRepository;
    private final LocaleMessageResource messageResource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(e -> new SecurityUserDetails(e.getUsername(), e.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException(messageResource.getMessage("auth.username.password.incorrect")));
    }
}
