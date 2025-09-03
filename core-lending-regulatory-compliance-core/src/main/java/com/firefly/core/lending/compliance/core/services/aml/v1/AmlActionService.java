package com.firefly.core.lending.compliance.core.services.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlActionDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AmlActionService {

    /**
     * Retrieves a paginated list of AML actions associated with a specific AML case,
     * filtered by the provided criteria.
     *
     * @param amlCaseId the unique identifier of the AML case whose actions are to be retrieved
     * @param filterRequest the filtering and pagination criteria for querying the AML actions
     * @return a reactive Mono containing a PaginationResponse object with a list of matching
     *         AmlActionDTO objects and associated pagination metadata
     */
    Mono<PaginationResponse<AmlActionDTO>> findAll(UUID amlCaseId, FilterRequest<AmlActionDTO> filterRequest);

    /**
     * Creates a new AML action associated with the specified AML case ID using the provided details.
     *
     * @param amlCaseId the unique identifier of the AML case to which the action belongs
     * @param dto the {@link AmlActionDTO} object containing the details of the AML action to be created
     * @return a {@link Mono} emitting the created {@link AmlActionDTO} object upon successful creation
     */
    Mono<AmlActionDTO> create(UUID amlCaseId, AmlActionDTO dto);

    /**
     * Retrieves a specific AML action by its unique identifier within a particular AML case.
     *
     * @param amlCaseId the unique identifier of the AML case to which the action belongs
     * @param amlActionId the unique identifier of the AML action to be retrieved
     * @return a Mono emitting the requested AmlActionDTO if found, otherwise an empty Mono
     */
    Mono<AmlActionDTO> getById(UUID amlCaseId, UUID amlActionId);

    /**
     * Updates an existing AML action for a specific AML case using the provided data.
     *
     * @param amlCaseId the unique identifier of the AML case to which the AML action belongs
     * @param amlActionId the unique identifier of the AML action to be updated
     * @param dto the {@link AmlActionDTO} object containing the updated details of the AML action
     * @return a {@link Mono} emitting the updated {@link AmlActionDTO} object upon successful update, or an error if the update fails
     */
    Mono<AmlActionDTO> update(UUID amlCaseId, UUID amlActionId, AmlActionDTO dto);

    /**
     * Deletes an AML action associated with a specific AML case, identified by their unique identifiers.
     *
     * @param amlCaseId the unique identifier of the AML case associated with the action to be deleted
     * @param amlActionId the unique identifier of the AML action to be deleted
     * @return a {@link Mono} signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID amlCaseId, UUID amlActionId);
}
