package com.genesis.application.contacts.domain.exception;

import org.springframework.http.HttpStatus;
/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public class BusinessValidationException extends ContactsException {

    public BusinessValidationException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason, ExceptionCode.BUSINESS_VALIDATION_ERROR);
    }

    public BusinessValidationException(ExceptionCode code) {
        super(HttpStatus.BAD_REQUEST, code.getDescription(), code);
    }

    public BusinessValidationException(String reason, ExceptionCode code){
        super(HttpStatus.BAD_REQUEST, reason, code);
    }
}
