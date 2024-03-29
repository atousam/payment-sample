package org.sample.payment.service.auth;

import lombok.RequiredArgsConstructor;
import org.sample.payment.cache.CacheNames;
import org.sample.payment.dao.entity.TokenEntity;
import org.sample.payment.dao.repo.ITokenRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
@RequiredArgsConstructor
@Service
public class TokenService {
    private final ITokenRepository tokenRepository;

    @Cacheable(value = CacheNames.TOKENS, key = "#token")
    public Optional<TokenEntity> findTokenEntity(String token) {
        return tokenRepository.findByToken(token);
    }
}
