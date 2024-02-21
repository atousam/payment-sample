package org.sample.payment.security;

import lombok.RequiredArgsConstructor;
import org.sample.payment.dao.repo.IUserRepository;
import org.sample.payment.message.LocaleMessageResource;
import org.sample.payment.service.auth.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
@RequiredArgsConstructor
public class SecurityDaoUserDetails implements UserDetailsService {
    private final UserService userService;
    private final LocaleMessageResource messageResource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userService.getUser(username))
                .map(e -> new SecurityUserDetails(e.getUsername(), e.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException(messageResource.getMessage("auth.username.password.incorrect")));
    }
}
