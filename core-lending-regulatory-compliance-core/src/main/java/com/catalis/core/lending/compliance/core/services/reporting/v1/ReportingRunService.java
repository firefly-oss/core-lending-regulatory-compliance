package com.catalis.core.lending.compliance.core.services.reporting.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRunDTO;
import reactor.core.publisher.Mono;

public interface ReportingRunService {

    /**
     * Retrieves a paginated list of reporting runs based on the provided filtering criteria.
     *
     * @param filterRequest the filter request containing filtering parameters and pagination details for the reporting runs
     * @return a reactive Mono containing a PaginationResponse object that encapsulates the list of matching ReportingRunDTO objects along with pagination metadata
     */
    Mono<PaginationResponse<ReportingRunDTO>> findAll(FilterRequest<ReportingRunDTO> filterRequest);

    /**
     * Creates a new reporting run based on the provided details.
     *
     * @param dto the {@link ReportingRunDTO} representing the details of the new reporting run to be created
     * @return a {@link Mono} emitting the created {@link ReportingRunDTO} once the operation is completed
     */
    Mono<ReportingRunDTO> create(ReportingRunDTO dto);

    /**
     * Retrieves a ReportingRunDTO by its unique identifier.
     *
     * @param reportingRunId the unique identifier of the reporting run
     * @return a Mono emitting the requested ReportingRunDTO if found, otherwise an empty Mono
     */
    Mono<ReportingRunDTO> getById(Long reportingRunId);

    /**
     * Updates an existing reporting run identified by its ID with the provided data.
     *
     * @param reportingRunId the unique identifier of the reporting run to be updated
     * @param dto the new data to update the reporting run with
     * @return a Mono emitting the updated ReportingRunDTO object, or an error if the update fails
     */
    Mono<ReportingRunDTO> update(Long reportingRunId, ReportingRunDTO dto);

    /**
     * Deletes a reporting run by its unique identifier.
     *
     * @param reportingRunId the unique identifier of the reporting run to be deleted
     * @return a Mono signaling the completion of the delete operation
     */
    Mono<Void> delete(Long reportingRunId);
}
