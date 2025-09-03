package com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.CurrencyCodeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.RecordTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportingRecordDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID reportingRecordId;

    @FilterableId
    @NotNull(message = "Reporting run ID is required")
    private UUID reportingRunId;

    @FilterableId
    @NotNull(message = "Loan servicing case ID is required")
    private UUID loanServicingCaseId;

    @FilterableId
    @NotNull(message = "Customer ID is required")
    private UUID customerId;

    @FilterableId
    @NotNull(message = "Transaction ID is required")
    private UUID transactionId;

    @NotNull(message = "Record type is required")
    private RecordTypeEnum recordType;

    @NotNull(message = "Reported amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Reported amount must be greater than zero")
    @Digits(integer = 15, fraction = 2, message = "Reported amount must have at most 15 integer digits and 2 decimal places")
    private BigDecimal reportedAmount;

    @NotNull(message = "Currency code is required")
    private CurrencyCodeEnum currencyCode;

    @Size(max = 5000, message = "Additional data cannot exceed 5000 characters")
    private String additionalData;

    @NotNull(message = "Is included flag is required")
    private Boolean isIncluded;

    @Size(max = 500, message = "Exclusion reason cannot exceed 500 characters")
    private String exclusionReason;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}