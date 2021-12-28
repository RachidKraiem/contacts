package com.genesis.application.contacts.rest.frontdto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@SuperBuilder
public class ContactFrontDto implements FrontDto {

    private String                  businessKey;

    @NotBlank
    private String                  firstName;

    @NotBlank
    private String                  lastName;

    @NotBlank
    private String                  address;

    private String                  numberVAT;

    private Set<EnterpriseFrontDto> enterprises;

}
