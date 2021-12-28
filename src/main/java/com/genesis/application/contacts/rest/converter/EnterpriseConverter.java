package com.genesis.application.contacts.rest.converter;

import org.springframework.stereotype.Component;

import com.genesis.application.contacts.domain.entity.Enterprise;
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
public class EnterpriseConverter implements Converter<Enterprise, EnterpriseFrontDto> {

    @Override
    public EnterpriseFrontDto toFrontDto(Enterprise domain) {
        return EnterpriseFrontDto.builder().businessKey(domain.getBusinessKey()).numberVAT(domain.getNumberVAT())
                .address(domain.getAddress()).build();
    }

    @Override
    public Enterprise toDomain(EnterpriseFrontDto frontDto) {
        return Enterprise.builder().businessKey(frontDto.getBusinessKey()).address(frontDto.getAddress())
                .numberVAT(frontDto.getNumberVAT()).build();
    }

}
