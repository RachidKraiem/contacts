package com.genesis.application.contacts.dataaccess.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.genesis.application.contacts.domain.BasicEntity;



/**
 *
 *
 * @author "Rachid KRAIEM"
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@NoRepositoryBean
public interface AbstractRepository<T extends BasicEntity, I> extends JpaRepository<T, I> {

    Optional<T> findByBusinessKey(String businessKey);

    Optional<T> findById(long id);

}
