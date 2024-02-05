package org.sample.payment.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.payment.dao.entity.TokenEntity;
import org.sample.payment.dao.repo.ITokenRepository;
import org.sample.payment.dao.repo.IUserRepository;
import org.sample.payment.dto.auth.LoginRequestDto;
import org.sample.payment.dto.auth.LoginResponseDto;
import org.sample.payment.exception.InvalidUsernamePasswordException;
import org.sample.payment.message.LocaleMessageResource;
import org.sample.payment.security.TokenAuthFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final ITokenRepository tokenRepository;
    private final IUserRepository userRepository;
    private final LocaleMessageResource messageResource;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationValidation(loginRequestDto);
        String token = generateToken(loginRequestDto.getUsername());
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(token);
        return responseDto;
    }

    private void authenticationValidation(LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDto.getUsername(), loginRequestDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            log.error("Invalid username/password ", e);
            throw new InvalidUsernamePasswordException("20", messageResource.getMessage("auth.username.password.incorrect"));
        }
    }

    private String generateToken(String username) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(UUID.randomUUID().toString());
        tokenEntity.setToken(UUID.randomUUID().toString());
        tokenEntity.setUser(userRepository.findByUsername(username).get());
        tokenRepository.save(tokenEntity);
        return TokenAuthFilter.BEARER_TOKEN + tokenEntity.getToken();
    }
}
