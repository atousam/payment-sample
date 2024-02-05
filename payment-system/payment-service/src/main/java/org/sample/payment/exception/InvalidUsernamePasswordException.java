package org.sample.payment.exception;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
public class InvalidUsernamePasswordException extends BusinessException {
    public InvalidUsernamePasswordException(String errorCode, String message) {
        super(errorCode, message);
    }
}
