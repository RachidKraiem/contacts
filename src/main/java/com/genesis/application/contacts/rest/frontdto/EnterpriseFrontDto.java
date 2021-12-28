package com.genesis.application.contacts.rest.frontdto;

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
public class EnterpriseFrontDto implements FrontDto {

    private String businessKey;

    @NotBlank
    private String numberVAT;

    @NotBlank
    private String address;

}
