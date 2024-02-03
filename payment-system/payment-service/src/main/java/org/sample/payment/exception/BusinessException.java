package org.sample.payment.exception;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String message;
}
