package com.genesis.application.contacts.rest.exception;

import com.genesis.application.contacts.domain.exception.ExceptionCode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ExceptionFrontDto {

    private final ExceptionType type;
    private final String        code;
    private final String        description;
    private final String        detailMessage;

    public ExceptionFrontDto(@NonNull ExceptionType type, @NonNull ExceptionCode exceptionCode, String detailMessage) {
        this.type = type;
        code = exceptionCode.getCode();
        description = exceptionCode.getDescription();
        this.detailMessage = detailMessage;
    }

    public ExceptionFrontDto(@NonNull ExceptionType type, @NonNull ExceptionCode exceptionCode) {
        this.type = type;
        code = exceptionCode.getCode();
        description = exceptionCode.getDescription();
        detailMessage = "";
    }

}
