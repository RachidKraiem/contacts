package com.genesis.application.contacts.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesis.application.contacts.dataaccess.persistence.EmployeeRepository;
import com.genesis.application.contacts.dataaccess.persistence.FreelanceRepository;
import com.genesis.application.contacts.domain.entity.Contact;
import com.genesis.application.contacts.domain.entity.Employee;
import com.genesis.application.contacts.domain.entity.Freelance;
import com.genesis.application.contacts.domain.enums.ContactType;
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
public class ContactService {

    @Autowired
    private FreelanceRepository freelanceRepository;

    @Autowired
    private EmployeeRepository  employeeRepository;

    public Contact save(@NonNull Contact contact) {
        if (ContactType.EMPLOYEE.equals(contact.getType())) {
            return employeeRepository.save((Employee) contact);
        }
        return freelanceRepository.save((Freelance) contact);
    }

    public Contact update(@NonNull Contact contact) {
        if (ContactType.EMPLOYEE.equals(contact.getType())) {
            Employee existing = findEmployeeByBusinessKey(contact.getBusinessKey());
            if (Objects.isNull(existing)) {
                throw new BusinessValidationException(ExceptionCode.CONTACT_NOT_FOUND);
            }
            existing.update((Employee) contact);
            return employeeRepository.save(existing);
        }
        Freelance existing = findFreelanceByBusinessKey(contact.getBusinessKey());
        existing.update((Freelance) contact);
        return freelanceRepository.save(existing);
    }

    public void delete(@NonNull String businessKey) {
        Employee employee = findEmployeeByBusinessKey(businessKey);
        if (Objects.nonNull(employee)) {
            employeeRepository.delete(employee);
        } else {
            Freelance freelance = findFreelanceByBusinessKey(businessKey);
            freelanceRepository.delete(freelance);
        }
    }

    public List<Freelance> findAllFreelance() {
        return freelanceRepository.findAll();
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    Freelance findFreelanceByBusinessKey(@NonNull String bk) {
        return freelanceRepository.findByBusinessKey(bk)
                .orElseThrow(() -> new BusinessValidationException(ExceptionCode.CONTACT_NOT_FOUND));
    }

    Employee findEmployeeByBusinessKey(@NonNull String bk) {
        return employeeRepository.findByBusinessKey(bk).orElse(null);
    }

}
