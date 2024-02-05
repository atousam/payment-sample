package org.sample.payment.dao.entity;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "USER")
@Setter
@Getter
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
