package com.firefly.core.lending.compliance.interfaces.dtos.aml.v1;

import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.SarStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmlSarDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long amlSarId;

    @FilterableId
    private Long amlCaseId;

    private String sarReference;
    private LocalDate sarDate;
    private SarStatusEnum sarStatus;
    private String authorityFeedback;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
