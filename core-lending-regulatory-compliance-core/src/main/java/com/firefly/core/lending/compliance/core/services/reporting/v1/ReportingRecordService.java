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


package com.firefly.core.lending.compliance.core.services.reporting.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRecordDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
    Mono<PaginationResponse<ReportingRecordDTO>> findAll(UUID reportingRunId, FilterRequest<ReportingRecordDTO> filterRequest);

    /**
     * Creates a new reporting record within the specified reporting run.
     *
     * @param reportingRunId the unique identifier of the reporting run to which the new record belongs
     * @param dto the {@link ReportingRecordDTO} object containing details of the reporting record to be created
     * @return a {@link Mono} emitting the created {@link ReportingRecordDTO} upon successful creation
     */
    Mono<ReportingRecordDTO> create(UUID reportingRunId, ReportingRecordDTO dto);

    /**
     * Retrieves a specific reporting record by its unique identifier within a particular reporting run.
     *
     * @param reportingRunId the unique identifier of the reporting run to which the record belongs
     * @param reportingRecordId the unique identifier of the reporting record to be retrieved
     * @return a Mono emitting the requested ReportingRecordDTO if found, otherwise an empty Mono
     */
    Mono<ReportingRecordDTO> getById(UUID reportingRunId, UUID reportingRecordId);

    /**
     * Updates an existing reporting record within a specific reporting run, based on the provided identifiers and data.
     *
     * @param reportingRunId the unique identifier of the reporting run containing the reporting record to be updated
     * @param reportingRecordId the unique identifier of the reporting record to be updated
     * @param dto the new data to update the reporting record with, represented as a ReportingRecordDTO
     * @return a Mono emitting the updated ReportingRecordDTO object, or an error if the update fails
     */
    Mono<ReportingRecordDTO> update(UUID reportingRunId, UUID reportingRecordId, ReportingRecordDTO dto);

    /**
     * Deletes a reporting record identified by its unique reporting run ID and reporting record ID.
     *
     * @param reportingRunId the unique identifier of the reporting run associated with the record to be deleted
     * @param reportingRecordId the unique identifier of the reporting record to be deleted
     * @return a Mono signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID reportingRunId, UUID reportingRecordId);
}
