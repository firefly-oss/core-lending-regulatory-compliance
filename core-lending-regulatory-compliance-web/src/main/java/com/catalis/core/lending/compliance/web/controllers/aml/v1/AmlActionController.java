package com.catalis.core.lending.compliance.web.controllers.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.compliance.core.services.aml.v1.AmlActionService;
import com.catalis.core.lending.compliance.interfaces.dtos.aml.v1.AmlActionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/aml-cases/{amlCaseId}/actions")
@Tag(name = "AmlAction", description = "Operations on actions within an AML case")
@RequiredArgsConstructor
public class AmlActionController {

    private final AmlActionService service;

    @GetMapping
    @Operation(summary = "List or search AML actions for a case")
    public Mono<ResponseEntity<PaginationResponse<AmlActionDTO>>> findAll(
            @PathVariable Long amlCaseId,
            @ModelAttribute FilterRequest<AmlActionDTO> filterRequest) {

        return service.findAll(amlCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new AML action")
    public Mono<ResponseEntity<AmlActionDTO>> create(
            @PathVariable Long amlCaseId,
            @RequestBody AmlActionDTO dto) {

        return service.create(amlCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{amlActionId}")
    @Operation(summary = "Get an AML action by ID")
    public Mono<ResponseEntity<AmlActionDTO>> getById(
            @PathVariable Long amlCaseId,
            @PathVariable Long amlActionId) {

        return service.getById(amlCaseId, amlActionId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{amlActionId}")
    @Operation(summary = "Update an AML action")
    public Mono<ResponseEntity<AmlActionDTO>> update(
            @PathVariable Long amlCaseId,
            @PathVariable Long amlActionId,
            @RequestBody AmlActionDTO dto) {

        return service.update(amlCaseId, amlActionId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{amlActionId}")
    @Operation(summary = "Delete an AML action")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long amlCaseId,
            @PathVariable Long amlActionId) {

        return service.delete(amlCaseId, amlActionId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}