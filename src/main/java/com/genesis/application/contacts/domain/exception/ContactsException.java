package com.genesis.application.contacts.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@SuppressWarnings("serial")
public class ContactsException extends RuntimeException implements CodifiedException {

    private final HttpStatus    status;
    private final ExceptionCode code;

    public ContactsException(HttpStatus status) {
        this(status, null, null, null);
    }

    public ContactsException(String message) {
        this(null, message, null, null);
    }

    public ContactsException(String message, Throwable cause) {
        this(null, message, cause, translateToCode(cause));
    }

    public ContactsException(Throwable cause, ExceptionCode code) {
        this(null, null, cause, code);
    }

    public ContactsException(HttpStatus status, String message, ExceptionCode code) {
        this(status, message, null, code);
    }

    public ContactsException(HttpStatus status, String message, Throwable cause, ExceptionCode code) {
        super(message, cause);
        if (status != null) {
            this.status = status;
        } else {
            this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (code != null) {
            this.code = code;
        } else {
            this.code = ExceptionCode.DEFAULT_CODE;
        }
    }

    private static ExceptionCode translateToCode(Throwable cause) {
        if (cause == null) {
            return null;
        }
        if (CodifiedException.class.isAssignableFrom(cause.getClass())) {
            return ((CodifiedException) cause).getCode();
        }
        if (cause.getCause() != null) {
            return translateToCode(cause.getCause());
        }
        return null;
    }

}
