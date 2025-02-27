package com.catalis.core.lending.compliance.models.entities.reporting.v1;

import com.catalis.core.lending.compliance.interfaces.enums.reporting.v1.SubmissionStatusEnum;
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
@Table("regulatory_submission")
public class RegulatorySubmission {

    @Id
    @Column("regulatory_submission_id")
    private Long regulatorySubmissionId;

    @Column("reporting_run_id")
    private Long reportingRunId;

    @Column("submission_ref")
    private String submissionRef;

    @Column("submission_date")
    private LocalDate submissionDate;

    @Column("submission_status")
    private SubmissionStatusEnum submissionStatus;

    @Column("response_details")
    private String responseDetails;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
