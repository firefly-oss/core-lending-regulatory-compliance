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


package com.firefly.core.lending.compliance.interfaces.dtos.aml.v1;

import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.AmlCaseStatusEnum;
import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.RiskLevelEnum;
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
public class AmlCaseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID amlCaseId;

    @FilterableId
    @NotNull(message = "Reporting run ID is required")
    private UUID reportingRunId;

    @FilterableId
    @NotNull(message = "Customer ID is required")
    private UUID customerId;

    @FilterableId
    @NotNull(message = "Transaction ID is required")
    private UUID transactionId;

    @NotNull(message = "AML case status is required")
    private AmlCaseStatusEnum amlCaseStatus;

    @NotNull(message = "Case opened date is required")
    @PastOrPresent(message = "Case opened date cannot be in the future")
    private LocalDate caseOpenedAt;

    @PastOrPresent(message = "Case closed date cannot be in the future")
    private LocalDate caseClosedAt;

    @NotNull(message = "Risk level is required")
    private RiskLevelEnum riskLevel;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}