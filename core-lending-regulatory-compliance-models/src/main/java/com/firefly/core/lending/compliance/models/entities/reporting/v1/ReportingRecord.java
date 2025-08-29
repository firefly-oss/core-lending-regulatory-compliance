package com.firefly.core.lending.compliance.models.entities.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.CurrencyCodeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.RecordTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("reporting_record")
public class ReportingRecord {

    @Id
    @Column("reporting_record_id")
    private Long reportingRecordId;

    @Column("reporting_run_id")
    private Long reportingRunId;

    // External references (no direct FK in DB):
    @Column("loan_servicing_case_id")
    private Long loanServicingCaseId;

    @Column("customer_id")
    private Long customerId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("record_type")
    private RecordTypeEnum recordType;

    @Column("reported_amount")
    private BigDecimal reportedAmount;

    @Column("currency_code")
    private CurrencyCodeEnum currencyCode;

    @Column("additional_data")
    private String additionalData;

    @Column("is_included")
    private Boolean isIncluded;

    @Column("exclusion_reason")
    private String exclusionReason;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

