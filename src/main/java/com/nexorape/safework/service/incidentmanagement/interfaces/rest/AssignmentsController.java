package com.nexorape.safework.service.incidentmanagement.interfaces.rest;

import com.nexorape.safework.service.incidentmanagement.domain.model.queries.assignment.GetAllAssignmentsQuery;
import com.nexorape.safework.service.incidentmanagement.domain.model.queries.assignment.GetAssignmentByIdQuery;
import com.nexorape.safework.service.incidentmanagement.domain.services.AssignmentQueryService;
import com.nexorape.safework.service.incidentmanagement.domain.services.IncidentCommandService;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.assignment.AssignmentResource;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.resources.assignment.CreateAssignmentResource;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.transform.assignment.AssignmentResourceFromEntityAssembler;
import com.nexorape.safework.service.incidentmanagement.interfaces.rest.transform.assignment.CreateAssignmentCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/assignments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Assignments", description = "Assignments Management Endpoints")
public class AssignmentsController {
    private final IncidentCommandService incidentCommandService;
    private final AssignmentQueryService assignmentQueryService;

    public AssignmentsController(IncidentCommandService incidentCommandService,
            AssignmentQueryService assignmentQueryService) {
        this.incidentCommandService = incidentCommandService;
        this.assignmentQueryService = assignmentQueryService;
    }

    @PostMapping
    @Operation(summary = "Create A New Assignment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Assignment created"),
            @ApiResponse(responseCode = "400", description = "Bad request") })
    public ResponseEntity<AssignmentResource> createAssignment(@RequestBody CreateAssignmentResource resource) {
        var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication();
        var userDetails = (com.nexorape.safework.service.iam.infrastructure.authorization.sfs.model.UserDetailsImpl) authentication
                .getPrincipal();

        var createAssignmentCommand = CreateAssignmentCommandFromResourceAssembler.toCommandFromResource(resource,
                userDetails.getId());

        var assignment = incidentCommandService.handle(createAssignmentCommand);

        // Validacion simple
        if (assignment.isEmpty())
            return ResponseEntity.badRequest().build();

        var createdAssignment = assignment.get();

        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(createdAssignment);

        return new ResponseEntity<>(assignmentResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Assignments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Assignments fetched!"),
            @ApiResponse(responseCode = "400", description = "Bad request") })
    public ResponseEntity getAllAssignments() {
        var getAllAssignmentsQuery = new GetAllAssignmentsQuery();
        var assignments = assignmentQueryService.handle(getAllAssignmentsQuery);
        var assignmentsResource = assignments.stream().map(AssignmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(assignmentsResource);
    }

    @GetMapping("/{assignmentId}")
    @Operation(summary = "Get Assignment By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A assignment has been fetched!"),
            @ApiResponse(responseCode = "400", description = "Bad request") })
    public ResponseEntity<AssignmentResource> getAssignmentById(@PathVariable Long assignmentId) {
        var getAssignmentByIdQuery = new GetAssignmentByIdQuery(assignmentId);
        var assignment = assignmentQueryService.handle(getAssignmentByIdQuery);
        if (assignment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment.get());
        return ResponseEntity.ok(assignmentResource);
    }

}
