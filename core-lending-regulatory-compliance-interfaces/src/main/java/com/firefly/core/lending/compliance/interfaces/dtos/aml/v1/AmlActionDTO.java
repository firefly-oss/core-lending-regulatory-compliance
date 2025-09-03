package com.firefly.core.lending.compliance.interfaces.dtos.aml.v1;

import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.AmlActionTypeEnum;
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
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmlActionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID amlActionId;

    @FilterableId
    @NotNull(message = "AML case ID is required")
    private UUID amlCaseId;

    @NotNull(message = "Action date is required")
    @PastOrPresent(message = "Action date cannot be in the future")
    private LocalDateTime actionDate;

    @NotNull(message = "Action type is required")
    private AmlActionTypeEnum actionType;

    @NotBlank(message = "Outcome is required")
    @Size(max = 1000, message = "Outcome cannot exceed 1000 characters")
    private String outcome;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}