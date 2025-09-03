package com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.SubmissionStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegulatorySubmissionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID regulatorySubmissionId;

    @FilterableId
    @NotNull(message = "Reporting run ID is required")
    private UUID reportingRunId;

    @NotBlank(message = "Submission reference is required")
    @Size(max = 100, message = "Submission reference cannot exceed 100 characters")
    private String submissionRef;

    @NotNull(message = "Submission date is required")
    @PastOrPresent(message = "Submission date cannot be in the future")
    private LocalDate submissionDate;

    @NotNull(message = "Submission status is required")
    private SubmissionStatusEnum submissionStatus;

    @Size(max = 2000, message = "Response details cannot exceed 2000 characters")
    private String responseDetails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}