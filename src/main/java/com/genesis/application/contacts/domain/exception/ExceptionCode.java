package com.genesis.application.contacts.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ExceptionCode {

    DEFAULT_CODE("GEN_1000", "Internal error occurred"),
    BAD_REQUEST_ERROR("GEN_1001", "Bad request error"),
    BUSINESS_VALIDATION_ERROR("GEN_1002", "A business constraint has failed on the data provided"),
    CONTACT_NOT_FOUND("GEN_1003", "cannot find contact"),
    ENTERPRISE_NOT_FOUND("GEN_1004", "cannot find enterprise");

    private final String code;

    @NonNull
    private final String description;
}
