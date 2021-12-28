package com.genesis.application.contacts.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.genesis.application.contacts.domain.BasicEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
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
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "enterprise")
public class Enterprise extends BasicEntity {

    private static final long serialVersionUID = 7328814381315029373L;

    @NonNull
    @Column(name = "number_vat")
    private String            numberVAT;

    @NonNull
    @Column(name = "address")
    private String            address;

    public void update(Enterprise enterprise) {
        setAddress(enterprise.getAddress());
        setNumberVAT(enterprise.getNumberVAT());
    }

}
