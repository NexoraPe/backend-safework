package com.nexorape.safework.service.incidentmanagement.domain.model.aggregates;

import com.nexorape.safework.service.incidentmanagement.domain.model.commands.CreateIncidentCommand;
import com.nexorape.safework.service.incidentmanagement.domain.model.valueobjects.incident.IncidentStatus;
import com.nexorape.safework.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Entity
public class Incident extends AuditableAbstractAggregateRoot<Incident> {

    // ATRIBUTOS
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

    // METODOS
    public String getStatus() {
        return status.name();
    }
}
