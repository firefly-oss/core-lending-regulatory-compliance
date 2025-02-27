package com.catalis.core.lending.compliance.models.entities.reporting.v1;

import com.catalis.core.lending.compliance.interfaces.enums.reporting.v1.ReportTypeEnum;
import com.catalis.core.lending.compliance.interfaces.enums.reporting.v1.RunStatusEnum;
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
@Table("reporting_run")
public class ReportingRun {

    @Id
    @Column("reporting_run_id")
    private Long reportingRunId;

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

