package com.catalis.core.lending.compliance.core.mappers.aml.v1;

import com.catalis.core.lending.compliance.interfaces.dtos.aml.v1.AmlActionDTO;
import com.catalis.core.lending.compliance.models.entities.aml.v1.AmlAction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AmlActionMapper {
    AmlActionDTO toDTO(AmlAction entity);
    AmlAction toEntity(AmlActionDTO dto);
}