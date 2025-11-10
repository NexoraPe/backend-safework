package com.nexorape.safework.service.IAM.domain.model.aggregates;

import com.nexorape.safework.service.IAM.domain.model.entities.Role;
import com.nexorape.safework.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.nexorape.safework.service.IAM.domain.model.valueobjects.user.EmailAddress;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    // ATRIBUTOS LMAO
    /**/
    //@Embedded
    //@AttributeOverrides({
    //        @AttributeOverride(name = "companyId", column = @Column(name = "fk_company_id"))})
    //private CompanyId companyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company_id", nullable = false)
    private Company company;

    /**/
    @Getter
    @NotBlank
    @Size(max = 120)
    private String fullName;

    /**/
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email"))})
    private EmailAddress emailAddress;

    @Getter
    private String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Getter
    private String phoneNumber;

    @Getter
    private String profilePictureUrl;

    // CONSTRUCTORES
    /**
     * Default constructor
     */
    public User() {
        this.roles = new HashSet<>();
    }

    /**
     * Sobrecargado constructor
     */
    public User(Company company, String fullName, EmailAddress emailAddress, String passwordHash, List<Role> roles) {
        this.company = company;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        addRoles(roles);
    }

    // METODOS
    //public void updatePersonalInfo(String fullName, String phoneNumber) {
    //    this.fullName = fullName;
    //    this.phoneNumber = phoneNumber;
    //}
//
    //public void updateProfilePicture(String profilePictureUrl) {
    //    this.profilePictureUrl = profilePictureUrl;
    //}
//
    //public void updatePasswordHash(String passwordHash) {
    //    this.passwordHash = passwordHash;
    //}

    /**
     * Add a role to the user
     * @param role the role to add
     * @return the user with the added role
     */
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    /**
     * Add a list of roles to the user
     * @param roles the list of roles to add
     * @return the user with the added roles
     */
    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }

    public Long getCompanyId(){
        return this.company.getId();
    }

    public String getEmail(){
        return this.emailAddress.address();
    }
}
