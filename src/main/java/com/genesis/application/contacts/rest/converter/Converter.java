package com.genesis.application.contacts.rest.converter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.genesis.application.contacts.rest.frontdto.FrontDto;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Converter<D, F extends FrontDto> {
    F toFrontDto(D domain);

    default Set<F> toFrontDtos(Collection<D> domain) {
        return domain.stream()
                .map(this::toFrontDto)
                .collect(Collectors.toSet());
    }

    D toDomain(F frontDto);

    default Set<D> toDomains(Collection<F> frontDtos) {
        return frontDtos.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }
}
