package com.catalis.core.lending.compliance.models.entities.aml.v1;

import com.catalis.core.lending.compliance.interfaces.enums.aml.v1.AmlActionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("aml_action")
public class AmlAction {

    @Id
    @Column("aml_action_id")
    private Long amlActionId;

    @Column("aml_case_id")
    private Long amlCaseId;

    @Column("action_date")
    private LocalDateTime actionDate;

    @Column("action_type")
    private AmlActionTypeEnum actionType;

    @Column("outcome")
    private String outcome;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}