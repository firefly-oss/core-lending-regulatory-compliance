package com.catalis.core.lending.compliance.web.controllers.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.compliance.core.services.aml.v1.AmlSarService;
import com.catalis.core.lending.compliance.interfaces.dtos.aml.v1.AmlSarDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/aml-cases/{amlCaseId}/sars")
@Tag(name = "AmlSar", description = "Operations on SAR (Suspicious Activity Reports) within an AML case")
@RequiredArgsConstructor
public class AmlSarController {

    private final AmlSarService service;

    @GetMapping
    @Operation(summary = "List or search SARs for a given AML case")
    public Mono<ResponseEntity<PaginationResponse<AmlSarDTO>>> findAll(
            @PathVariable Long amlCaseId,
            @ModelAttribute FilterRequest<AmlSarDTO> filterRequest) {

        return service.findAll(amlCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new SAR (Suspicious Activity Report)")
    public Mono<ResponseEntity<AmlSarDTO>> create(
            @PathVariable Long amlCaseId,
            @RequestBody AmlSarDTO dto) {

        return service.create(amlCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{amlSarId}")
    @Operation(summary = "Get a SAR by ID")
    public Mono<ResponseEntity<AmlSarDTO>> getById(
            @PathVariable Long amlCaseId,
            @PathVariable Long amlSarId) {

        return service.getById(amlCaseId, amlSarId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{amlSarId}")
    @Operation(summary = "Update a SAR record")
    public Mono<ResponseEntity<AmlSarDTO>> update(
            @PathVariable Long amlCaseId,
            @PathVariable Long amlSarId,
            @RequestBody AmlSarDTO dto) {

        return service.update(amlCaseId, amlSarId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{amlSarId}")
    @Operation(summary = "Delete a SAR record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long amlCaseId,
            @PathVariable Long amlSarId) {

        return service.delete(amlCaseId, amlSarId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}