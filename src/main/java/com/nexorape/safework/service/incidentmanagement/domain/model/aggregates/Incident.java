package com.nexorape.safework.service.incidentmanagement.domain.model.aggregates;

import com.nexorape.safework.service.iam.domain.model.aggregates.Company;
import com.nexorape.safework.service.iam.domain.model.aggregates.User;
import com.nexorape.safework.service.incidentmanagement.domain.model.commands.incident.CreateIncidentCommand;
import com.nexorape.safework.service.incidentmanagement.domain.model.entities.Assignment;
import com.nexorape.safework.service.incidentmanagement.domain.model.valueobjects.incident.IncidentStatus;
import com.nexorape.safework.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Incident extends AuditableAbstractAggregateRoot<Incident> {

    // ATRIBUTOS
    /**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private User user;

    /**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company_id", nullable = false)
    private Company company;

    /**/
    @Getter
    private String title;

    /**/
    @Getter
    private String description;

    /**/
    @Getter
    private String location;

    /**/
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentStatus status;

    /**/
    @Getter
    private String documentUrl;

    /*
     * mappedBy = "incident": Yo no tengo la FK, la tiene la clase Assignment en el
     * campo "incident"
     */
    @Getter
    @OneToOne(mappedBy = "incident", cascade = CascadeType.ALL)
    private Assignment assignment;

    // CONSTRUCTORES
    public Incident() {
        super();
        this.status = IncidentStatus.OPEN;
    }

    public Incident(CreateIncidentCommand command) {
        this();
        this.title = command.title();
        this.description = command.description();
        this.location = command.location();
    }

    public Incident(User user, Company company, CreateIncidentCommand command) {
        this();
        this.user = user;
        this.company = company;
        this.title = command.title();
        this.description = command.description();
        this.location = command.location();
    }

    // METODOS
    public String getStatus() {
        return status.name();
    }

    public Long getCompanyId() {
        return company.getId();
    }

    public Long getUserId() {
        return user.getId();
    }

    // METODOS
    public void assignTo(User user) {
        if (this.status == IncidentStatus.CLOSED) {
            throw new IllegalStateException("No se puede asignar un caso cerrado");
        }

        this.assignment = new Assignment(this, user);

        this.status = IncidentStatus.ASSIGNED;
    }

    public void startProgress() {
        if (this.status != IncidentStatus.ASSIGNED) {
            throw new IllegalStateException("Incident must be ASSIGNED to start progress.");
        }
        this.status = IncidentStatus.IN_PROGRESS;
    }

    public void close() {
        if (this.status != IncidentStatus.IN_PROGRESS) {
            throw new IllegalStateException("Incident must be IN_PROGRESS to close.");
        }
        this.status = IncidentStatus.CLOSED;
    }

}
