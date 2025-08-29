package com.firefly.core.lending.compliance.core.mappers.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRunDTO;
import com.firefly.core.lending.compliance.models.entities.reporting.v1.ReportingRun;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportingRunMapper {
    ReportingRunDTO toDTO(ReportingRun entity);
    ReportingRun toEntity(ReportingRunDTO dto);
}