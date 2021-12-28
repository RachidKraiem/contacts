package com.genesis.application.contacts.rest.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.application.contacts.domain.entity.Enterprise;
import com.genesis.application.contacts.rest.converter.ContactConverter;
import com.genesis.application.contacts.rest.converter.EnterpriseConverter;
import com.genesis.application.contacts.rest.frontdto.ContactFrontDto;
import com.genesis.application.contacts.rest.frontdto.EnterpriseFrontDto;
import com.genesis.application.contacts.service.EnterpriseService;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/api/enterprise")
public class EnterpriseRestController {

    @Autowired
    private EnterpriseService   enterpriseService;

    @Autowired
    private EnterpriseConverter enterpriseConverter;

    @Autowired
    private ContactConverter    contactConverter;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public EnterpriseFrontDto create(@RequestBody EnterpriseFrontDto enterpriseFrontDto) {
        enterpriseFrontDto.validate();
        Enterprise enterprise = enterpriseConverter.toDomain(enterpriseFrontDto);
        return enterpriseConverter.toFrontDto(enterpriseService.save(enterprise));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping
    public EnterpriseFrontDto update(@RequestBody EnterpriseFrontDto enterpriseFrontDto) {
        enterpriseFrontDto.validate();
        Enterprise enterprise = enterpriseConverter.toDomain(enterpriseFrontDto);
        return enterpriseConverter.toFrontDto(enterpriseService.update(enterprise));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{numberVAT}/search")
    public EnterpriseFrontDto search(@PathVariable(name = "numberVAT") String numberVAT) {
        return enterpriseConverter.toFrontDto(enterpriseService.search(numberVAT));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/all")
    public Set<EnterpriseFrontDto> findAll() {
        return enterpriseConverter.toFrontDtos(enterpriseService.findAllEnterprise());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{enterpriseBk}/contact/{contactBk}/add")
    public ContactFrontDto addContact(@PathVariable(name = "enterpriseBk") String enterpriseBk,
            @PathVariable(name = "contactBk") String contactBk) {
        return contactConverter.toFrontDto(enterpriseService.addContact(enterpriseBk, contactBk));
    }

}
