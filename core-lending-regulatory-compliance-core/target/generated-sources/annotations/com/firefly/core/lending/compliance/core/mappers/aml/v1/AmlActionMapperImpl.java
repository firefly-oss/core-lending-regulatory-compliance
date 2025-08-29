package com.firefly.core.lending.compliance.core.mappers.aml.v1;

import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlActionDTO;
import com.firefly.core.lending.compliance.models.entities.aml.v1.AmlAction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:43:09+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class AmlActionMapperImpl implements AmlActionMapper {

    @Override
    public AmlActionDTO toDTO(AmlAction entity) {
        if ( entity == null ) {
            return null;
        }

        AmlActionDTO.AmlActionDTOBuilder amlActionDTO = AmlActionDTO.builder();

        amlActionDTO.amlActionId( entity.getAmlActionId() );
        amlActionDTO.amlCaseId( entity.getAmlCaseId() );
        amlActionDTO.actionDate( entity.getActionDate() );
        amlActionDTO.actionType( entity.getActionType() );
        amlActionDTO.outcome( entity.getOutcome() );
        amlActionDTO.createdAt( entity.getCreatedAt() );
        amlActionDTO.updatedAt( entity.getUpdatedAt() );

        return amlActionDTO.build();
    }

    @Override
    public AmlAction toEntity(AmlActionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AmlAction.AmlActionBuilder amlAction = AmlAction.builder();

        amlAction.amlActionId( dto.getAmlActionId() );
        amlAction.amlCaseId( dto.getAmlCaseId() );
        amlAction.actionDate( dto.getActionDate() );
        amlAction.actionType( dto.getActionType() );
        amlAction.outcome( dto.getOutcome() );
        amlAction.createdAt( dto.getCreatedAt() );
        amlAction.updatedAt( dto.getUpdatedAt() );

        return amlAction.build();
    }
}
