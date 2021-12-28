package com.genesis.application.contacts.dataaccess.persistence;

import java.util.Optional;

import com.genesis.application.contacts.domain.entity.Enterprise;

/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EnterpriseRepository extends AbstractRepository<Enterprise, Long> {

    Optional<Enterprise> findByNumberVAT(String numberVAT);

}
