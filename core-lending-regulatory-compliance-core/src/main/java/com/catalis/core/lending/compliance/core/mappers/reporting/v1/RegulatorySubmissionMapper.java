package com.catalis.core.lending.compliance.core.mappers.reporting.v1;

import com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1.RegulatorySubmissionDTO;
import com.catalis.core.lending.compliance.models.entities.reporting.v1.RegulatorySubmission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegulatorySubmissionMapper {
    RegulatorySubmissionDTO toDTO(RegulatorySubmission entity);
    RegulatorySubmission toEntity(RegulatorySubmissionDTO dto);
}