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


package com.firefly.core.lending.compliance.web.controllers.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.core.services.aml.v1.AmlCaseService;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlCaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/aml-cases")
@Tag(name = "AmlCase", description = "Operations on AML (Anti-Money Laundering) Cases")
@RequiredArgsConstructor
public class AmlCaseController {

    private final AmlCaseService service;

    @GetMapping
    @Operation(summary = "List or search AML cases")
    public Mono<ResponseEntity<PaginationResponse<AmlCaseDTO>>> findAll(
            @ModelAttribute FilterRequest<AmlCaseDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new AML case")
    public Mono<ResponseEntity<AmlCaseDTO>> create(@RequestBody AmlCaseDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{amlCaseId}")
    @Operation(summary = "Get an AML case by ID")
    public Mono<ResponseEntity<AmlCaseDTO>> getById(@PathVariable UUID amlCaseId) {
        return service.getById(amlCaseId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{amlCaseId}")
    @Operation(summary = "Update an AML case")
    public Mono<ResponseEntity<AmlCaseDTO>> update(
            @PathVariable UUID amlCaseId,
            @RequestBody AmlCaseDTO dto) {

        return service.update(amlCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{amlCaseId}")
    @Operation(summary = "Delete an AML case")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID amlCaseId) {
        return service.delete(amlCaseId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
