package org.sample.payment.dto.error;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponseDto {
    private String error;
    private String message;
}
