package com.catalis.core.lending.compliance.interfaces.dtos.aml.v1;

import com.catalis.core.lending.compliance.interfaces.enums.aml.v1.AmlActionTypeEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmlActionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long amlActionId;

    @FilterableId
    private Long amlCaseId;

    private LocalDateTime actionDate;
    private AmlActionTypeEnum actionType;
    private String outcome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}