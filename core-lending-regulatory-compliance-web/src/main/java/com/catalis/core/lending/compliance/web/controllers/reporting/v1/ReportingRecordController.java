package com.catalis.core.lending.compliance.web.controllers.reporting.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.compliance.core.services.reporting.v1.ReportingRecordService;
import com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/reporting-runs/{reportingRunId}/records")
@Tag(name = "ReportingRecord", description = "Operations for Reporting Records within a Reporting Run")
@RequiredArgsConstructor
public class ReportingRecordController {

    private final ReportingRecordService service;

    @GetMapping
    @Operation(summary = "List or search reporting records for a specific run")
    public Mono<ResponseEntity<PaginationResponse<ReportingRecordDTO>>> findAll(
            @PathVariable Long reportingRunId,
            @ModelAttribute FilterRequest<ReportingRecordDTO> filterRequest) {

        return service.findAll(reportingRunId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new reporting record")
    public Mono<ResponseEntity<ReportingRecordDTO>> create(
            @PathVariable Long reportingRunId,
            @RequestBody ReportingRecordDTO dto) {

        return service.create(reportingRunId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{reportingRecordId}")
    @Operation(summary = "Get a reporting record by ID")
    public Mono<ResponseEntity<ReportingRecordDTO>> getById(
            @PathVariable Long reportingRunId,
            @PathVariable Long reportingRecordId) {

        return service.getById(reportingRunId, reportingRecordId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{reportingRecordId}")
    @Operation(summary = "Update a reporting record")
    public Mono<ResponseEntity<ReportingRecordDTO>> update(
            @PathVariable Long reportingRunId,
            @PathVariable Long reportingRecordId,
            @RequestBody ReportingRecordDTO dto) {

        return service.update(reportingRunId, reportingRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{reportingRecordId}")
    @Operation(summary = "Delete a reporting record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long reportingRunId,
            @PathVariable Long reportingRecordId) {

        return service.delete(reportingRunId, reportingRecordId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
