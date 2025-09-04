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
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("aml_case")
public class AmlCase {

    @Id
    @Column("aml_case_id")
    private UUID amlCaseId;

    @Column("reporting_run_id")
    private UUID reportingRunId;

    @Column("customer_id")
    private UUID customerId;

    @Column("transaction_id")
    private UUID transactionId;

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