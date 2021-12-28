package com.genesis.application.contacts.rest.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.application.contacts.domain.entity.Contact;
import com.genesis.application.contacts.rest.converter.ContactConverter;
import com.genesis.application.contacts.rest.converter.EmployeeConverter;
import com.genesis.application.contacts.rest.converter.FreelanceConverter;
import com.genesis.application.contacts.rest.frontdto.ContactFrontDto;
import com.genesis.application.contacts.service.ContactService;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */

@RestController
@RequestMapping(value = "/api/contact")
public class ContactRestController {

    @Autowired
    private ContactService     contactService;

    @Autowired
    private ContactConverter   contactConverter;

    @Autowired
    private EmployeeConverter  employeeConverter;

    @Autowired
    private FreelanceConverter freelanceConverter;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ContactFrontDto create(@RequestBody ContactFrontDto contactFrontDto) {
        contactFrontDto.validate();
        Contact contact = contactConverter.toDomain(contactFrontDto);
        return contactConverter.toFrontDto(contactService.save(contact));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping
    public ContactFrontDto update(@RequestBody ContactFrontDto contactFrontDto) {
        contactFrontDto.validate();
        Contact contact = contactConverter.toDomain(contactFrontDto);
        return contactConverter.toFrontDto(contactService.update(contact));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{bk}/delete")
    public void softDelete(@PathVariable(name = "bk") String businessKey) {
        contactService.delete(businessKey);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/all")
    public Set<ContactFrontDto> findAll() {
        Set<ContactFrontDto> freelances = freelanceConverter.toFrontDtos(contactService.findAllFreelance());
        Set<ContactFrontDto> employees = employeeConverter.toFrontDtos(contactService.findAllEmployee());
        freelances.addAll(employees);
        return freelances;
    }

}
