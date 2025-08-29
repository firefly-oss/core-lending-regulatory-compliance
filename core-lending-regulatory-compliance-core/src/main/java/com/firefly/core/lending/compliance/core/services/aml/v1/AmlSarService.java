package com.firefly.core.lending.compliance.core.services.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlSarDTO;
import reactor.core.publisher.Mono;

public interface AmlSarService {

    /**
     * Retrieves a paginated list of AML SAR (Suspicious Activity Report) records associated with a specific AML case,
     * filtered based on the provided criteria.
     *
     * @param amlCaseId the unique identifier of the AML case whose SAR records are to be retrieved
     * @param filterRequest the filtering and pagination criteria for querying the AML SAR records, encapsulated in a {@link FilterRequest} object
     * @return a reactive Mono containing a {@link PaginationResponse} object with a list of matching {@link AmlSarDTO} objects and associated pagination metadata
     */
    Mono<PaginationResponse<AmlSarDTO>> findAll(Long amlCaseId, FilterRequest<AmlSarDTO> filterRequest);

    /**
     * Creates a new AML SAR (Suspicious Activity Report) associated with the specified AML case ID.
     *
     * @param amlCaseId the unique identifier of the AML case to which the SAR is associated
     * @param dto the {@link AmlSarDTO} object containing the details of the SAR to be created
     * @return a {@link Mono} emitting the created {@link AmlSarDTO} object upon successful creation
     */
    Mono<AmlSarDTO> create(Long amlCaseId, AmlSarDTO dto);

    /**
     * Retrieves an AML SAR (Suspicious Activity Report) resource by its unique identifier
     * within the context of a specific AML case.
     *
     * @param amlCaseId the unique identifier of the AML case to which the SAR belongs
     * @param amlSarId the unique identifier of the SAR to be retrieved
     * @return a {@link Mono} emitting the requested {@link AmlSarDTO} if found,
     *         or an empty {@link Mono} if the SAR does not exist
     */
    Mono<AmlSarDTO> getById(Long amlCaseId, Long amlSarId);

    /**
     * Updates an existing AML SAR (Suspicious Activity Report) record associated with a specific AML case
     * and identified by its unique SAR ID, using the provided data.
     *
     * @param amlCaseId the unique identifier of the AML case to which the SAR belongs
     * @param amlSarId the unique identifier of the SAR to be updated
     * @param dto the {@link AmlSarDTO} object containing the updated details for the SAR
     * @return a {@link Mono} emitting the updated {@link AmlSarDTO} object upon successful update, or an error if the update fails
     */
    Mono<AmlSarDTO> update(Long amlCaseId, Long amlSarId, AmlSarDTO dto);

    /**
     * Deletes a specific AML SAR entry associated with a given AML case.
     *
     * @param amlCaseId the unique identifier of the AML case to which the AML SAR belongs
     * @param amlSarId the unique identifier of the AML SAR to be deleted
     * @return a {@link Mono} signaling the completion of the delete operation
     */
    Mono<Void> delete(Long amlCaseId, Long amlSarId);
}