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
import com.firefly.core.lending.compliance.core.services.reporting.v1.ReportingRunService;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRunDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reporting-runs")
@Tag(name = "ReportingRun", description = "Operations for Reporting Runs")
@RequiredArgsConstructor
public class ReportingRunController {

    private final ReportingRunService service;

    @GetMapping
    @Operation(summary = "List or search reporting runs")
    public Mono<ResponseEntity<PaginationResponse<ReportingRunDTO>>> findAll(
            @ModelAttribute FilterRequest<ReportingRunDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new reporting run")
    public Mono<ResponseEntity<ReportingRunDTO>> create(@RequestBody ReportingRunDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{reportingRunId}")
    @Operation(summary = "Get a reporting run by ID")
    public Mono<ResponseEntity<ReportingRunDTO>> getById(@PathVariable UUID reportingRunId) {
        return service.getById(reportingRunId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{reportingRunId}")
    @Operation(summary = "Update a reporting run")
    public Mono<ResponseEntity<ReportingRunDTO>> update(
            @PathVariable UUID reportingRunId,
            @RequestBody ReportingRunDTO dto) {

        return service.update(reportingRunId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{reportingRunId}")
    @Operation(summary = "Delete a reporting run")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID reportingRunId) {
        return service.delete(reportingRunId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
