package org.sample.payment.dao.repo;

import org.sample.payment.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */
@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
