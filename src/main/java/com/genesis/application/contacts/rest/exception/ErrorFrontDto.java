package com.genesis.application.contacts.rest.exception;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.Assert;

import lombok.NonNull;
import lombok.Value;

@Value
public class ErrorFrontDto {

    private Collection<ExceptionFrontDto> errors = new ArrayList<>();

    public ErrorFrontDto(@NonNull Collection<ExceptionFrontDto> errors) {
        Assert.notEmpty(errors, "Cannot create error resource with no error");
        this.errors.addAll(errors);
    }

    public ErrorFrontDto(@NonNull ExceptionFrontDto error) {
        errors.add(error);
    }
}
