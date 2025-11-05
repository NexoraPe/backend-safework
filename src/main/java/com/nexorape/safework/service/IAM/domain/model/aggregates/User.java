package com.nexorape.safework.service.IAM.domain.model.aggregates;

import com.nexorape.safework.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.nexorape.safework.service.shared.domain.model.valueobjects.UserId;
import com.nexorape.safework.service.shared.domain.model.valueobjects.CompanyId;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.EmailAddress;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.Role;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;


@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @Getter
    @Embedded
    private UserId userUuid;

    @Getter
    @Embedded
    private CompanyId companyId;

    @Getter
    private String fullName;

    @Getter
    @Embedded
    private EmailAddress emailAddress;

    @Getter
    private String passwordHash;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    private String phoneNumber;

    @Getter
    private String profilePictureUrl;

    /**
     * Default constructor
     */
    public User() {
        super();
        this.userUuid = new UserId();
    }


    public User(Long companyId, String fullName, String emailAddress, String passwordHash, String role) {
        this();
        this.companyId = new CompanyId(companyId);
        this.fullName = fullName;
        this.emailAddress = new EmailAddress(emailAddress);
        this.passwordHash = passwordHash;
        this.role = Role.valueOf(role.toUpperCase())  ;
    }

    public void updatePersonalInfo(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public void updateProfilePicture(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void updatePasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
