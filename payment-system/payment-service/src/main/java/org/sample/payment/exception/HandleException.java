package org.sample.payment.exception;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/3/2024
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.payment.dto.error.ErrorResponseDto;
import org.sample.payment.message.LocaleMessageResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class HandleException {
    private final LocaleMessageResource messageResource;

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleServiceExceptions(BusinessException e) {
        log.warn("Process was not successful with code:{}, message:{}", e.getErrorCode(), e.getMessage());
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(e.getMessage());
        responseDto.setError(e.getErrorCode());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundBusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundServiceExceptions(NotFoundBusinessException e) {
        log.warn("Process was not successful with code:{}, message:{}", e.getErrorCode(), e.getMessage());
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(e.getMessage());
        responseDto.setError(e.getErrorCode());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnhandledExceptions(Exception e) {
        log.error("Unhandled exception, ", e);
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(messageResource.getMessage("general.unhandled.exception"));
        responseDto.setError("-1000");
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
