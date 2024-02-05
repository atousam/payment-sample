package org.sample.payment.exception;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(String errorCode, String message) {
        super(errorCode, message);
    }
}
