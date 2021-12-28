package com.genesis.application.contacts.rest.frontdto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.genesis.application.contacts.domain.exception.BusinessValidationException;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FrontDto {

    default void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<FrontDto>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            ConstraintViolation<FrontDto> violation = violations.iterator().next();
            throw new BusinessValidationException(
                    violation.getPropertyPath().toString() + ": " + violation.getMessage());
        }
    }

}
