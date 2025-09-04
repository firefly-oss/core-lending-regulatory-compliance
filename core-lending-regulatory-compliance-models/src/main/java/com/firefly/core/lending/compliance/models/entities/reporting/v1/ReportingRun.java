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


package com.firefly.core.lending.compliance.models.entities.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.ReportTypeEnum;
import com.firefly.core.lending.compliance.interfaces.enums.reporting.v1.RunStatusEnum;
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
@Table("reporting_run")
public class ReportingRun {

    @Id
    @Column("reporting_run_id")
    private UUID reportingRunId;

    @Column("report_type")
    private ReportTypeEnum reportType;

    @Column("reporting_period_start")
    private LocalDate reportingPeriodStart;

    @Column("reporting_period_end")
    private LocalDate reportingPeriodEnd;

    @Column("run_status")
    private RunStatusEnum runStatus;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

