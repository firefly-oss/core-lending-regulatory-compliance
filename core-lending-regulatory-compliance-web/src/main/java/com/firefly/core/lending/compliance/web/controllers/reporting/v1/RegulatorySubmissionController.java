/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reporting-runs/{reportingRunId}/submissions")
@Tag(name = "RegulatorySubmission", description = "Operations for Regulatory Submissions within a Reporting Run")
@RequiredArgsConstructor
public class RegulatorySubmissionController {

    private final RegulatorySubmissionService service;

    @GetMapping
    @Operation(summary = "List or search regulatory submissions for a specific run")
    public Mono<ResponseEntity<PaginationResponse<RegulatorySubmissionDTO>>> findAll(
            @PathVariable UUID reportingRunId,
            @ModelAttribute FilterRequest<RegulatorySubmissionDTO> filterRequest) {

        return service.findAll(reportingRunId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new regulatory submission")
    public Mono<ResponseEntity<RegulatorySubmissionDTO>> create(
            @PathVariable UUID reportingRunId,
            @RequestBody RegulatorySubmissionDTO dto) {

        return service.create(reportingRunId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{regulatorySubmissionId}")
    @Operation(summary = "Get a regulatory submission by ID")
    public Mono<ResponseEntity<RegulatorySubmissionDTO>> getById(
            @PathVariable UUID reportingRunId,
            @PathVariable UUID regulatorySubmissionId) {

        return service.getById(reportingRunId, regulatorySubmissionId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{regulatorySubmissionId}")
    @Operation(summary = "Update a regulatory submission")
    public Mono<ResponseEntity<RegulatorySubmissionDTO>> update(
            @PathVariable UUID reportingRunId,
            @PathVariable UUID regulatorySubmissionId,
            @RequestBody RegulatorySubmissionDTO dto) {

        return service.update(reportingRunId, regulatorySubmissionId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{regulatorySubmissionId}")
    @Operation(summary = "Delete a regulatory submission record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID reportingRunId,
            @PathVariable UUID regulatorySubmissionId) {

        return service.delete(reportingRunId, regulatorySubmissionId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
