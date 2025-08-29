package com.firefly.core.lending.compliance.interfaces.dtos.aml.v1;

import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.AmlCaseStatusEnum;
import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.RiskLevelEnum;
import com.firefly.core.utils.annotations.FilterableId;
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
public class AmlCaseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long amlCaseId;

    @FilterableId
    private Long reportingRunId;

    @FilterableId
    private Long customerId;

    @FilterableId
    private Long transactionId;

    private AmlCaseStatusEnum amlCaseStatus;
    private LocalDate caseOpenedAt;
    private LocalDate caseClosedAt;
    private RiskLevelEnum riskLevel;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}