package com.genesis.application.contacts.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EqualsAndHashCode
@ToString
@SuperBuilder
public class BasicEntity implements Serializable {

    private static final long serialVersionUID = -5376207358208910415L;

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @Setter(value = AccessLevel.PRIVATE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long              id;

    @Getter
    @Setter(value = AccessLevel.PUBLIC)
    @Column(unique = true, nullable = false, length = BusinessKeyGenerator.BK_LENGTH)
    private String            businessKey;

    @EqualsAndHashCode.Exclude
    @Column(name = "created_on")
    private LocalDateTime     createdOn;

    @EqualsAndHashCode.Exclude
    @Column(name = "modified_on")
    private LocalDateTime     modifiedOn;


    protected BasicEntity(String businessKey) {
        this.businessKey = businessKey;
    }

    @PrePersist
    @PreUpdate
    private void onPrePersistOrUpdate() {
        modifiedOn = LocalDateTime.now();
        if (createdOn == null) {
            createdOn = modifiedOn;
        }
        if (null == businessKey) {
            businessKey = BusinessKeyGenerator.generateBusinessKey(this.getClass());
        }
    }
}
