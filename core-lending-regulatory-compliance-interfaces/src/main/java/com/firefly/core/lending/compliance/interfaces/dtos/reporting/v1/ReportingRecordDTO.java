package com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.CurrencyCodeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.RecordTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportingRecordDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long reportingRecordId;

    @FilterableId
    private Long reportingRunId;

    @FilterableId
    private Long loanServicingCaseId;

    @FilterableId
    private Long customerId;

    @FilterableId
    private Long transactionId;

    private RecordTypeEnum recordType;
    private BigDecimal reportedAmount;
    private CurrencyCodeEnum currencyCode;
    private String additionalData;
    private Boolean isIncluded;
    private String exclusionReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}