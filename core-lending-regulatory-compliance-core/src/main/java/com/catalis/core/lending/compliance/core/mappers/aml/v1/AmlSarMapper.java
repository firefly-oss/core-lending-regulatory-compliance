package com.catalis.core.lending.compliance.core.mappers.aml.v1;

import com.catalis.core.lending.compliance.interfaces.dtos.aml.v1.AmlSarDTO;
import com.catalis.core.lending.compliance.models.entities.aml.v1.AmlSar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AmlSarMapper {
    AmlSarDTO toDTO(AmlSar entity);
    AmlSar toEntity(AmlSarDTO dto);
}
