package com.firefly.core.lending.compliance.models.entities.aml.v1;

import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.AmlCaseStatusEnum;
import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.RiskLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("aml_case")
public class AmlCase {

    @Id
    @Column("aml_case_id")
    private Long amlCaseId;

    @Column("reporting_run_id")
    private Long reportingRunId;

    @Column("customer_id")
    private Long customerId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("aml_case_status")
    private AmlCaseStatusEnum amlCaseStatus;

    @Column("case_opened_at")
    private LocalDate caseOpenedAt;

    @Column("case_closed_at")
    private LocalDate caseClosedAt;

    @Column("risk_level")
    private RiskLevelEnum riskLevel;

    @Column("notes")
    private String notes;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}