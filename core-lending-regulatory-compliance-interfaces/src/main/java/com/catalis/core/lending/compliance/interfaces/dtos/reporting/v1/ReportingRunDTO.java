package com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1;

import com.catalis.core.lending.compliance.interfaces.enums.reporting.v1.ReportTypeEnum;
import com.catalis.core.lending.compliance.interfaces.enums.reporting.v1.RunStatusEnum;
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
public class ReportingRunDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long reportingRunId;

    private ReportTypeEnum reportType;
    private LocalDate reportingPeriodStart;
    private LocalDate reportingPeriodEnd;
    private RunStatusEnum runStatus;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

