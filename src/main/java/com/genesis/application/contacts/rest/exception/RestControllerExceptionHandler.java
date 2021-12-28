package com.genesis.application.contacts.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.genesis.application.contacts.domain.exception.ContactsException;
import com.genesis.application.contacts.domain.exception.ExceptionCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.genesis.application.contacts.rest")
public class RestControllerExceptionHandler {

    private static ErrorFrontDto getErrorFrontDto(ExceptionType type, ExceptionCode code, String detailMessage) {
        return new ErrorFrontDto(new ExceptionFrontDto(type, code, detailMessage));
    }

    @ExceptionHandler(ContactsException.class)
    public ResponseEntity<ErrorFrontDto> handleError(ContactsException exception) {
        logException(exception);
        return ResponseEntity.status(exception.getStatus())
                .body(getErrorFrontDto(ExceptionType.ERROR, exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorFrontDto> handleError(IllegalArgumentException exception) {
        logException(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(getErrorFrontDto(ExceptionType.ERROR, ExceptionCode.BAD_REQUEST_ERROR, exception.getMessage()));
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<ErrorFrontDto> handleError(ServletRequestBindingException exception) {
        logException(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(getErrorFrontDto(ExceptionType.ERROR, ExceptionCode.BAD_REQUEST_ERROR, exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorFrontDto> handleError(Exception exception) {
        logException(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getErrorFrontDto(ExceptionType.ERROR, ExceptionCode.DEFAULT_CODE, exception.getMessage()));
    }

    private void logException(Exception exception) {
        String handleErrorMessage = "Handle error: ";
        if (log.isTraceEnabled()) {
            log.trace(handleErrorMessage, exception);
        } else if (log.isDebugEnabled()) {
            log.error(handleErrorMessage, exception);
        } else {
            log.error(handleErrorMessage, exception);
        }
    }
}
