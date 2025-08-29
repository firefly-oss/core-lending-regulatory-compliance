package com.firefly.core.lending.compliance.core.services.reporting.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.RegulatorySubmissionDTO;
import reactor.core.publisher.Mono;

public interface RegulatorySubmissionService {

    /**
     * Retrieves a paginated list of regulatory submissions for a specific reporting run,
     * based on the provided filtering criteria.
     *
     * @param reportingRunId the unique identifier of the reporting run for which the regulatory submissions are to be retrieved
     * @param filterRequest the filtering and pagination criteria for querying the regulatory submissions
     * @return a reactive Mono containing a PaginationResponse object with the list of matching
     *         RegulatorySubmissionDTO objects and associated pagination metadata
     */
    Mono<PaginationResponse<RegulatorySubmissionDTO>> findAll(Long reportingRunId,
                                                              FilterRequest<RegulatorySubmissionDTO> filterRequest);

    /**
     * Creates a new regulatory submission for a specified reporting run.
     *
     * @param reportingRunId the unique identifier of the reporting run associated with the regulatory submission
     * @param dto the {@link RegulatorySubmissionDTO} containing the details of the regulatory submission to be created
     * @return a {@link Mono} emitting the created {@link RegulatorySubmissionDTO} object upon successful creation
     */
    Mono<RegulatorySubmissionDTO> create(Long reportingRunId, RegulatorySubmissionDTO dto);

    /**
     * Retrieves a specific regulatory submission by its unique identifiers.
     *
     * @param reportingRunId the unique identifier of the reporting run associated with the regulatory submission
     * @param regulatorySubmissionId the unique identifier of the regulatory submission to be retrieved
     * @return a Mono emitting the requested RegulatorySubmissionDTO if found, otherwise an empty Mono
     */
    Mono<RegulatorySubmissionDTO> getById(Long reportingRunId, Long regulatorySubmissionId);

    /**
     * Updates an existing regulatory submission identified by its reporting run ID and regulatory submission ID
     * with the provided data.
     *
     * @param reportingRunId the unique identifier of the reporting run to which the regulatory submission belongs
     * @param regulatorySubmissionId the unique identifier of the regulatory submission to be updated
     * @param dto the new data to update the regulatory submission, represented as a RegulatorySubmissionDTO
     * @return a Mono emitting the updated RegulatorySubmissionDTO object, or an error if the update fails
     */
    Mono<RegulatorySubmissionDTO> update(Long reportingRunId, Long regulatorySubmissionId,
                                         RegulatorySubmissionDTO dto);

    /**
     * Deletes a regulatory submission associated with the given reporting run and regulatory submission IDs.
     *
     * @param reportingRunId the unique identifier of the reporting run associated with the regulatory submission to be deleted
     * @param regulatorySubmissionId the unique identifier of the regulatory submission to be deleted
     * @return a Mono indicating the completion of the delete operation
     */
    Mono<Void> delete(Long reportingRunId, Long regulatorySubmissionId);
}
