package org.sample.payment.dto.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Atousa Mirhosseini
 * @since 02 Feb, 2024
 */
@Getter
@Setter
public class LoginRequestDto {
    private String username;
    private String password;
}
