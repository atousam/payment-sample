package org.sample.payment.exception;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

public class NotFoundBusinessException extends BusinessException {
    public NotFoundBusinessException(String errorCode, String message) {
        super(errorCode, message);
    }
}
