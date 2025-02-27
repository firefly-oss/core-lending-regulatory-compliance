package com.catalis.core.lending.compliance.core.mappers.aml.v1;

import com.catalis.core.lending.compliance.interfaces.dtos.aml.v1.AmlCaseDTO;
import com.catalis.core.lending.compliance.models.entities.aml.v1.AmlCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AmlCaseMapper {
    AmlCaseDTO toDTO(AmlCase entity);
    AmlCase toEntity(AmlCaseDTO dto);
}