package com.genesis.application.contacts.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.genesis.application.contacts.domain.BasicEntity;
import com.genesis.application.contacts.domain.enums.ContactType;

import lombok.AccessLevel;
import lombok.Builder;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "contact")
public abstract class Contact extends BasicEntity {

    private static final long serialVersionUID = -3033441205800823525L;

    @NonNull
    @Column(name = "first_name")
    private String            firstName;

    @NonNull
    @Column(name = "last_name")
    private String            lastName;

    @NonNull
    @Column(name = "address")
    private String            address;

    @ManyToMany
    @JoinTable(name = "contact_enterprise", joinColumns = @JoinColumn(name = "contact_id"),
    inverseJoinColumns = @JoinColumn(name = "enterprise_id"))
    @Builder.Default
    private Set<Enterprise>   enterprises = new HashSet<>();

    public abstract ContactType getType();

}
