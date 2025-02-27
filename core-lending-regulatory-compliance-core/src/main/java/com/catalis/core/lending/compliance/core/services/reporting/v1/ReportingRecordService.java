package com.catalis.core.lending.compliance.core.services.reporting.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRecordDTO;
import reactor.core.publisher.Mono;

public interface ReportingRecordService {

    /**
     * Retrieves a paginated list of reporting records associated with a specific reporting run
     * and filtered by the specified criteria.
     *
     * @param reportingRunId the unique identifier of the reporting run whose records are to be retrieved
     * @param filterRequest the filtering and pagination criteria for querying the reporting records
     * @return a reactive Mono containing a PaginationResponse object with the list of matching
     *         ReportingRecordDTO objects and associated pagination metadata
     */
    Mono<PaginationResponse<ReportingRecordDTO>> findAll(Long reportingRunId, FilterRequest<ReportingRecordDTO> filterRequest);

    /**
     * Creates a new reporting record within the specified reporting run.
     *
     * @param reportingRunId the unique identifier of the reporting run to which the new record belongs
     * @param dto the {@link ReportingRecordDTO} object containing details of the reporting record to be created
     * @return a {@link Mono} emitting the created {@link ReportingRecordDTO} upon successful creation
     */
    Mono<ReportingRecordDTO> create(Long reportingRunId, ReportingRecordDTO dto);

    /**
     * Retrieves a specific reporting record by its unique identifier within a particular reporting run.
     *
     * @param reportingRunId the unique identifier of the reporting run to which the record belongs
     * @param reportingRecordId the unique identifier of the reporting record to be retrieved
     * @return a Mono emitting the requested ReportingRecordDTO if found, otherwise an empty Mono
     */
    Mono<ReportingRecordDTO> getById(Long reportingRunId, Long reportingRecordId);

    /**
     * Updates an existing reporting record within a specific reporting run, based on the provided identifiers and data.
     *
     * @param reportingRunId the unique identifier of the reporting run containing the reporting record to be updated
     * @param reportingRecordId the unique identifier of the reporting record to be updated
     * @param dto the new data to update the reporting record with, represented as a ReportingRecordDTO
     * @return a Mono emitting the updated ReportingRecordDTO object, or an error if the update fails
     */
    Mono<ReportingRecordDTO> update(Long reportingRunId, Long reportingRecordId, ReportingRecordDTO dto);

    /**
     * Deletes a reporting record identified by its unique reporting run ID and reporting record ID.
     *
     * @param reportingRunId the unique identifier of the reporting run associated with the record to be deleted
     * @param reportingRecordId the unique identifier of the reporting record to be deleted
     * @return a Mono signaling the completion of the delete operation
     */
    Mono<Void> delete(Long reportingRunId, Long reportingRecordId);
}
