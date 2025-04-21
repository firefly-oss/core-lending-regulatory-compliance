package com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1;

import com.catalis.core.lending.compliance.interfaces.enums.reporting.v1.SubmissionStatusEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegulatorySubmissionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long regulatorySubmissionId;

    @FilterableId
    private Long reportingRunId;

    private String submissionRef;
    private LocalDate submissionDate;
    private SubmissionStatusEnum submissionStatus;
    private String responseDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}