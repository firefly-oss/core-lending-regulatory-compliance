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


package com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.ReportTypeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.RunStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportingRunDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID reportingRunId;

    @NotNull(message = "Report type is required")
    private ReportTypeEnum reportType;

    @NotNull(message = "Reporting period start date is required")
    @PastOrPresent(message = "Reporting period start date cannot be in the future")
    private LocalDate reportingPeriodStart;

    @NotNull(message = "Reporting period end date is required")
    @PastOrPresent(message = "Reporting period end date cannot be in the future")
    private LocalDate reportingPeriodEnd;

    @NotNull(message = "Run status is required")
    private RunStatusEnum runStatus;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

