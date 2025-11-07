package com.nexorape.safework.service.IAM.domain.model.aggregates;

import com.nexorape.safework.service.IAM.domain.model.valueobjects.RegistrationCode;
import com.nexorape.safework.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Company extends AuditableAbstractAggregateRoot<Company> {

    @Getter
    private String name;

    @Getter
    @Embedded
    @Column(nullable = false, unique = true)
    private RegistrationCode registrationCode;


    public Company() {
        super();
    }

    public Company(String name, String registrationCode) {
        this();
        this.name = name;
        this.registrationCode = new RegistrationCode(registrationCode);
    }

    public void updateName(String newName) {
        this.name = newName;
    }
    public void updateRegistrationCode(String newRegistrationCode) {
        this.registrationCode = new RegistrationCode(newRegistrationCode);
    }
}
