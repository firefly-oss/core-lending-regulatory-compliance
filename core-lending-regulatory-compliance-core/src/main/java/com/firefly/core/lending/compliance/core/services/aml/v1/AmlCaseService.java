package com.firefly.core.lending.compliance.core.services.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlCaseDTO;
import reactor.core.publisher.Mono;

public interface AmlCaseService {

    /**
     * Retrieves a paginated list of AML cases based on the provided filtering criteria.
     *
     * @param filterRequest the filtering and pagination criteria, encapsulated in a {@link FilterRequest}
     *                      object, which specifies the conditions for querying the AML cases
     * @return a reactive Mono containing a {@link PaginationResponse} object that includes a list of
     *         matching {@link AmlCaseDTO} objects along with pagination metadata
     */
    Mono<PaginationResponse<AmlCaseDTO>> findAll(FilterRequest<AmlCaseDTO> filterRequest);

    /**
     * Creates a new AML case with the provided details.
     *
     * @param dto the {@link AmlCaseDTO} object containing the details of the AML case to be created
     * @return a {@link Mono} emitting the created {@link AmlCaseDTO} object upon successful creation
     */
    Mono<AmlCaseDTO> create(AmlCaseDTO dto);

    /**
     * Retrieves an AML case by its unique identifier.
     *
     * @param amlCaseId the unique identifier of the AML case to be retrieved
     * @return a Mono emitting the requested AmlCaseDTO if found, otherwise an empty Mono
     */
    Mono<AmlCaseDTO> getById(Long amlCaseId);

    /**
     * Updates an existing AML case identified by its ID with the provided details.
     *
     * @param amlCaseId the unique identifier of the AML case to be updated
     * @param dto the new data to update the AML case with, represented as an {@link AmlCaseDTO}
     * @return a {@link Mono} emitting the updated {@link AmlCaseDTO} object, or an error if the update fails
     */
    Mono<AmlCaseDTO> update(Long amlCaseId, AmlCaseDTO dto);

    /**
     * Deletes an AML case identified by its unique AML case ID.
     *
     * @param amlCaseId the unique identifier of the AML case to be deleted
     * @return a Mono signaling the completion of the delete operation
     */
    Mono<Void> delete(Long amlCaseId);
}
