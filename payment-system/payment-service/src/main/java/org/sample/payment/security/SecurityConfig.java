package org.sample.payment.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.payment.dao.repo.IUserRepository;
import org.sample.payment.message.LocaleMessageResource;
import org.sample.payment.service.auth.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    public static final String[] unauthorizedPaths = {"/swagger-ui/*", "/v3/api-docs/*", "/v3/api-docs*", "/auth/login"};
    private final UserService userService;
    private final LocaleMessageResource messageResource;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .securityContext((securityContext) -> securityContext.requireExplicitSave(true))
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable().authorizeHttpRequests()
                .requestMatchers(unauthorizedPaths).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public TokenAuthFilter authFilter() {
        return new TokenAuthFilter();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new SecurityDaoUserDetails(userService, messageResource);
    }
}
