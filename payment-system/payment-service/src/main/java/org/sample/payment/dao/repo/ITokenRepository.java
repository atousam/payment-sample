package org.sample.payment.dao.repo;

import org.sample.payment.dao.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
@Repository
public interface ITokenRepository extends JpaRepository<TokenEntity, String> {

    Optional<TokenEntity> findByToken(String token);
}
