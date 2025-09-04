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

import com.firefly.core.lending.compliance.interfaces.enums.aml.v1.SarStatusEnum;
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
public class AmlSarDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID amlSarId;

    @FilterableId
    @NotNull(message = "AML case ID is required")
    private UUID amlCaseId;

    @NotBlank(message = "SAR reference is required")
    @Size(max = 100, message = "SAR reference cannot exceed 100 characters")
    private String sarReference;

    @NotNull(message = "SAR date is required")
    @PastOrPresent(message = "SAR date cannot be in the future")
    private LocalDate sarDate;

    @NotNull(message = "SAR status is required")
    private SarStatusEnum sarStatus;

    @Size(max = 2000, message = "Authority feedback cannot exceed 2000 characters")
    private String authorityFeedback;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
