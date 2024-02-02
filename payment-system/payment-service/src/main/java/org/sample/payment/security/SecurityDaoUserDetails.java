package org.sample.payment.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
public class SecurityDaoUserDetails implements UserDetailsService {
    private static final Map<String, UserDetails> users = Map.of("admin", new SecurityUserDetails("admin", "$2a$10$CKMnDf6IHm7f6WhQ1/QRQez22ND4fELjQD8vzizL63VWSu9pX0pe."));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.get(username);
    }
}
