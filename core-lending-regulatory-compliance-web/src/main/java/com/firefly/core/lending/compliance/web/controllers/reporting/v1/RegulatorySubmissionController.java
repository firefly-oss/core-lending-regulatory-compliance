package com.firefly.core.lending.compliance.web.controllers.reporting.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.core.services.reporting.v1.RegulatorySubmissionService;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.RegulatorySubmissionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/reporting-runs/{reportingRunId}/submissions")
@Tag(name = "RegulatorySubmission", description = "Operations for Regulatory Submissions within a Reporting Run")
@RequiredArgsConstructor
public class RegulatorySubmissionController {

    private final RegulatorySubmissionService service;

    @GetMapping
    @Operation(summary = "List or search regulatory submissions for a specific run")
    public Mono<ResponseEntity<PaginationResponse<RegulatorySubmissionDTO>>> findAll(
            @PathVariable Long reportingRunId,
            @ModelAttribute FilterRequest<RegulatorySubmissionDTO> filterRequest) {

        return service.findAll(reportingRunId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new regulatory submission")
    public Mono<ResponseEntity<RegulatorySubmissionDTO>> create(
            @PathVariable Long reportingRunId,
            @RequestBody RegulatorySubmissionDTO dto) {

        return service.create(reportingRunId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{regulatorySubmissionId}")
    @Operation(summary = "Get a regulatory submission by ID")
    public Mono<ResponseEntity<RegulatorySubmissionDTO>> getById(
            @PathVariable Long reportingRunId,
            @PathVariable Long regulatorySubmissionId) {

        return service.getById(reportingRunId, regulatorySubmissionId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{regulatorySubmissionId}")
    @Operation(summary = "Update a regulatory submission")
    public Mono<ResponseEntity<RegulatorySubmissionDTO>> update(
            @PathVariable Long reportingRunId,
            @PathVariable Long regulatorySubmissionId,
            @RequestBody RegulatorySubmissionDTO dto) {

        return service.update(reportingRunId, regulatorySubmissionId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{regulatorySubmissionId}")
    @Operation(summary = "Delete a regulatory submission record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long reportingRunId,
            @PathVariable Long regulatorySubmissionId) {

        return service.delete(reportingRunId, regulatorySubmissionId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
