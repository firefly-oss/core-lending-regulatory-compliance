package com.catalis.core.lending.compliance.core.mappers.reporting.v1;

import com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRecordDTO;
import com.catalis.core.lending.compliance.models.entities.reporting.v1.ReportingRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportingRecordMapper {
    ReportingRecordDTO toDTO(ReportingRecord entity);
    ReportingRecord toEntity(ReportingRecordDTO dto);
}
