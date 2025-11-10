package com.nexorape.safework.service.IAM.domain.model.aggregates;

//Por defecto
import com.nexorape.safework.service.IAM.domain.model.commands.company.CreateCompanyCommand;
import com.nexorape.safework.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

//Value Objects
import com.nexorape.safework.service.IAM.domain.model.valueobjects.company.RegistrationCode;


@Entity
public class Company extends AuditableAbstractAggregateRoot<Company> {

    // ATRIBUTOS LMAO
    /**/
    @Getter
    private String name;

    /**/
    @Embedded /*Es para value Objects, tiene que tener @Embeddable el value Object*/
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "registration_code"))})
    private RegistrationCode registrationCode;

    // CONSTRUCTORES
    /*Por defecto*/
    public Company() {
    }

    /*Sobrecargados*/
    public Company(String name) {
        this.name = name;
        this.registrationCode = new RegistrationCode();
    }

    public Company(CreateCompanyCommand command) {
        this.name = command.name();
        this.registrationCode = new RegistrationCode();
    }

    // METODOS
    /*GETTERS*/

    /**
     * Company name getter
     * @return Company name
     */
    public String getName() {
        return name;
    }

    /**
     * Company registrationCode getter
     * @return Company registrationCode
     */
    public String getRegistrationCode() {
        return registrationCode.code();
    }
}
