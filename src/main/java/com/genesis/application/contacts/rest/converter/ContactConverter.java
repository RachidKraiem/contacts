package com.genesis.application.contacts.rest.converter;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genesis.application.contacts.domain.entity.Contact;
import com.genesis.application.contacts.domain.entity.Employee;
import com.genesis.application.contacts.domain.entity.Freelance;
import com.genesis.application.contacts.domain.enums.ContactType;
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
public class ContactConverter implements Converter<Contact, ContactFrontDto> {

    @Autowired
    private EnterpriseConverter enterpriseConverter;

    @Override
    public ContactFrontDto toFrontDto(Contact domain) {
        Set<EnterpriseFrontDto> enterpriseFrontDtos = domain.getEnterprises().stream()
                .map(o -> enterpriseConverter.toFrontDto(o)).collect(Collectors.toSet());
        ContactFrontDto dto = ContactFrontDto.builder().businessKey(domain.getBusinessKey())
                .firstName(domain.getFirstName()).lastName(domain.getLastName()).address(domain.getAddress())
                .enterprises(enterpriseFrontDtos).build();
        if (ContactType.FREELANCE.equals(domain.getType())) {
            dto.setNumberVAT(((Freelance) domain).getNumberVAT());
        }
        return dto;
    }

    @Override
    public Contact toDomain(ContactFrontDto frontDto) {
        if (Objects.isNull(frontDto.getNumberVAT())) {
            return Employee.builder().businessKey(frontDto.getBusinessKey()).firstName(frontDto.getFirstName())
                    .lastName(frontDto.getLastName()).address(frontDto.getAddress()).build();
        }
        return Freelance.builder().businessKey(frontDto.getBusinessKey()).firstName(frontDto.getFirstName())
                .lastName(frontDto.getLastName()).address(frontDto.getAddress()).numberVAT(frontDto.getNumberVAT())
                .build();
    }

}
