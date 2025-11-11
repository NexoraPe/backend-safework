package com.nexorape.safework.service.incidentmanagement.interfaces.rest;


import com.nexorape.safework.service.incidentmanagement.domain.model.queries.GetAllIncidentsQuery;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.GetIncidentByIdQuery;
import com.nexorape.safework.service.incidentmanagement.domain.services.IncidentCommandService;
import com.nexorape.safework.service.incidentmanagement.domain.services.IncidentQueryService;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.incident.CreateIncidentResource;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.incident.IncidentResource;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.transform.incidents.CreateIncidentCommandFromResourceAssembler;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.transform.incidents.IncidentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/incidents", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Incidents", description = "Incidents Management Endpoints")
public class IncidentsController {
    private final IncidentCommandService incidentCommandService;
    private final IncidentQueryService incidentQueryService;

    public IncidentsController(IncidentCommandService incidentCommandService, IncidentQueryService incidentQueryService) {
        this.incidentCommandService = incidentCommandService;
        this.incidentQueryService = incidentQueryService;
    }


    @PostMapping
    @Operation(summary = "Create A New Incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incident created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<IncidentResource> createIncident(@RequestBody CreateIncidentResource resource){
        var createIncidentCommand = CreateIncidentCommandFromResourceAssembler.toCommandFromResource(resource);

        var incident = incidentCommandService.handle(createIncidentCommand);

        // Validacion simple
        if(incident.isEmpty()) return ResponseEntity.badRequest().build();

        var createdIncident = incident.get();

        var incidentResource = IncidentResourceFromEntityAssembler.toResourceFromEntity(createdIncident);

        return new ResponseEntity<>(incidentResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incidents fetched!"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity getAllIncidents(){
        var getAllIncidentsQuery = new GetAllIncidentsQuery();
        var incidents = incidentQueryService.handle(getAllIncidentsQuery);
        var incidentsResources = incidents.stream().map(IncidentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(incidentsResources);
    }

    @GetMapping("/{incidentId}")
    @Operation(summary = "Get Incident By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A incident has been fetched!"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<IncidentResource> getIncidentById(@PathVariable Long incidentId){
        var getIncidentByIdQuery = new GetIncidentByIdQuery(incidentId);
        var incident = incidentQueryService.handle(getIncidentByIdQuery);
        if(incident.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var incidentResource = IncidentResourceFromEntityAssembler.toResourceFromEntity(incident.get());
        return ResponseEntity.ok(incidentResource);
    }
}
