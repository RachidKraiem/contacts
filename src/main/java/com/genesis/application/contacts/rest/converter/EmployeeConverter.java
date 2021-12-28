package com.genesis.application.contacts.rest.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genesis.application.contacts.domain.entity.Employee;
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
public class EmployeeConverter implements Converter<Employee, ContactFrontDto> {

    @Autowired
    private EnterpriseConverter enterpriseConverter;

    @Override
    public ContactFrontDto toFrontDto(Employee domain) {
        Set<EnterpriseFrontDto> enterpriseFrontDtos = domain.getEnterprises().stream()
                .map(o -> enterpriseConverter.toFrontDto(o)).collect(Collectors.toSet());
        return ContactFrontDto.builder().businessKey(domain.getBusinessKey()).firstName(domain.getFirstName())
                .lastName(domain.getLastName()).address(domain.getAddress()).enterprises(enterpriseFrontDtos).build();
    }

    @Override
    public Employee toDomain(ContactFrontDto frontDto) {
        return Employee.builder().businessKey(frontDto.getBusinessKey()).firstName(frontDto.getFirstName())
                .lastName(frontDto.getLastName()).address(frontDto.getAddress()).build();
    }

}
