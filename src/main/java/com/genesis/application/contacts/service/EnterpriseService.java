package com.genesis.application.contacts.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesis.application.contacts.dataaccess.persistence.EnterpriseRepository;
import com.genesis.application.contacts.domain.entity.Contact;
import com.genesis.application.contacts.domain.entity.Employee;
import com.genesis.application.contacts.domain.entity.Enterprise;
import com.genesis.application.contacts.domain.entity.Freelance;
import com.genesis.application.contacts.domain.exception.BusinessValidationException;
import com.genesis.application.contacts.domain.exception.ExceptionCode;

import lombok.NonNull;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ContactService       contactService;

    public Enterprise save(@NonNull Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public Enterprise update(@NonNull Enterprise enterprise) {
        Enterprise existing = findByBusinessKey(enterprise.getBusinessKey());
        existing.update(enterprise);
        return enterpriseRepository.save(existing);
    }

    public Enterprise search(String numberVAT) {
        return enterpriseRepository.findByNumberVAT(numberVAT).orElseThrow(
                () -> new BusinessValidationException("Cannot find enterprise with VAT number " + numberVAT));
    }

    public List<Enterprise> findAllEnterprise() {
        return enterpriseRepository.findAll();
    }

    private Enterprise findByBusinessKey(@NonNull String bk) {
        return enterpriseRepository.findByBusinessKey(bk)
                .orElseThrow(() -> new BusinessValidationException(ExceptionCode.ENTERPRISE_NOT_FOUND));
    }

    @Transactional
    public Contact addContact(String enterpriseBk, String contactBk) {
        Enterprise enterprise = findByBusinessKey(enterpriseBk);
        Employee employee = contactService.findEmployeeByBusinessKey(contactBk);
        if (Objects.nonNull(employee)) {
            employee.getEnterprises().add(enterprise);
            return contactService.save(employee);
        } else {
            Freelance freelance = contactService.findFreelanceByBusinessKey(contactBk);
            freelance.getEnterprises().add(enterprise);
            return contactService.save(freelance);
        }
    }

}
