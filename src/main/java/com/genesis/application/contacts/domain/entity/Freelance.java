package com.genesis.application.contacts.domain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.genesis.application.contacts.domain.enums.ContactType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
@DiscriminatorValue("FREELANCE")
@Entity
public class Freelance extends Contact {

    private static final long serialVersionUID = -2472629152340978480L;

    @NonNull
    @Column(name = "number_vat")
    private String            numberVAT;

    @Override
    public ContactType getType() {
        return ContactType.FREELANCE;
    }

    public void update(Freelance freelance) {
        setFirstName(freelance.getFirstName());
        setLastName(freelance.getLastName());
        setAddress(freelance.getAddress());
        setNumberVAT(freelance.getNumberVAT());
    }

}
