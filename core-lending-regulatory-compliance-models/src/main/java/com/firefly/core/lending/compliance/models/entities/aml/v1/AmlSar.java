package com.firefly.core.lending.compliance.models.entities.aml.v1;

import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.SarStatusEnum;
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
@Table("aml_sar")
public class AmlSar {

    @Id
    @Column("aml_sar_id")
    private UUID amlSarId;

    @Column("aml_case_id")
    private UUID amlCaseId;

    @Column("sar_reference")
    private String sarReference;

    @Column("sar_date")
    private LocalDate sarDate;

    @Column("sar_status")
    private SarStatusEnum sarStatus;

    @Column("authority_feedback")
    private String authorityFeedback;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
