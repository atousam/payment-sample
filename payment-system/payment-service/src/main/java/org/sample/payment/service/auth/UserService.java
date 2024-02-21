package org.sample.payment.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.payment.cache.CacheNames;
import org.sample.payment.dao.entity.UserEntity;
import org.sample.payment.dao.repo.IUserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Atousa Mirhosseini
 * @since 09 Feb, 2024
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final IUserRepository userRepository;

    @Cacheable(value = CacheNames.USERS, key = "#username")
    public UserEntity getUser(String username) {
        log.debug("Start fetching user from db");
        return userRepository.findByUsername(username).orElse(null);
    }
}
