package com.genesis.application.contacts.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.genesis.application.contacts.domain.enums.ContactType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("EMPLOYEE")
@Entity
public class Employee extends Contact {

    private static final long serialVersionUID = -3540175347188333537L;

    @Override
    public ContactType getType() {
        return ContactType.EMPLOYEE;
    }

    public void update(Employee employee) {
        setFirstName(employee.getFirstName());
        setLastName(employee.getLastName());
        setAddress(employee.getAddress());
    }

}
