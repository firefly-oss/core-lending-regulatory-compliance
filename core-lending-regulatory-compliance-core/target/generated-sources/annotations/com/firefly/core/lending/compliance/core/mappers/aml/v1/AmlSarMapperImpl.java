package com.firefly.core.lending.compliance.core.mappers.aml.v1;

import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlSarDTO;
import com.firefly.core.lending.compliance.models.entities.aml.v1.AmlSar;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:45:20+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class AmlSarMapperImpl implements AmlSarMapper {

    @Override
    public AmlSarDTO toDTO(AmlSar entity) {
        if ( entity == null ) {
            return null;
        }

        AmlSarDTO.AmlSarDTOBuilder amlSarDTO = AmlSarDTO.builder();

        amlSarDTO.amlSarId( entity.getAmlSarId() );
        amlSarDTO.amlCaseId( entity.getAmlCaseId() );
        amlSarDTO.sarReference( entity.getSarReference() );
        amlSarDTO.sarDate( entity.getSarDate() );
        amlSarDTO.sarStatus( entity.getSarStatus() );
        amlSarDTO.authorityFeedback( entity.getAuthorityFeedback() );
        amlSarDTO.createdAt( entity.getCreatedAt() );
        amlSarDTO.updatedAt( entity.getUpdatedAt() );

        return amlSarDTO.build();
    }

    @Override
    public AmlSar toEntity(AmlSarDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AmlSar.AmlSarBuilder amlSar = AmlSar.builder();

        amlSar.amlSarId( dto.getAmlSarId() );
        amlSar.amlCaseId( dto.getAmlCaseId() );
        amlSar.sarReference( dto.getSarReference() );
        amlSar.sarDate( dto.getSarDate() );
        amlSar.sarStatus( dto.getSarStatus() );
        amlSar.authorityFeedback( dto.getAuthorityFeedback() );
        amlSar.createdAt( dto.getCreatedAt() );
        amlSar.updatedAt( dto.getUpdatedAt() );

        return amlSar.build();
    }
}
