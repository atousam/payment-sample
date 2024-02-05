package org.sample.payment.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
@Entity
@Table(name = "TOKEN")
@Setter
@Getter
@NoArgsConstructor
public class TokenEntity implements Serializable {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "USERID")
    private UserEntity user;
}
