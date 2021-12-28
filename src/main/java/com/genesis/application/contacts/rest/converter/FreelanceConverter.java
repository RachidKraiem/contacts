package com.genesis.application.contacts.rest.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genesis.application.contacts.domain.entity.Freelance;
import com.genesis.application.contacts.rest.frontdto.ContactFrontDto;
import com.genesis.application.contacts.rest.frontdto.EnterpriseFrontDto;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FreelanceConverter implements Converter<Freelance, ContactFrontDto> {

    @Autowired
    private EnterpriseConverter enterpriseConverter;

    @Override
    public ContactFrontDto toFrontDto(Freelance domain) {
        Set<EnterpriseFrontDto> enterpriseFrontDtos = domain.getEnterprises().stream()
                .map(o -> enterpriseConverter.toFrontDto(o)).collect(Collectors.toSet());
        return ContactFrontDto.builder().businessKey(domain.getBusinessKey()).firstName(domain.getFirstName())
                .lastName(domain.getLastName()).address(domain.getAddress()).numberVAT(domain.getNumberVAT())
                .enterprises(enterpriseFrontDtos).build();
    }

    @Override
    public Freelance toDomain(ContactFrontDto frontDto) {
        return Freelance.builder().businessKey(frontDto.getBusinessKey()).firstName(frontDto.getFirstName())
                .lastName(frontDto.getLastName()).address(frontDto.getAddress()).numberVAT(frontDto.getNumberVAT())
                .build();
    }
}
