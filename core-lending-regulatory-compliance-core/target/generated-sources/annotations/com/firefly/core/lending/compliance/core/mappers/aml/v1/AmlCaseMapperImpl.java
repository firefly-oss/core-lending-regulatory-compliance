package com.firefly.core.lending.compliance.core.mappers.aml.v1;

import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlCaseDTO;
import com.firefly.core.lending.compliance.models.entities.aml.v1.AmlCase;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:43:08+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class AmlCaseMapperImpl implements AmlCaseMapper {

    @Override
    public AmlCaseDTO toDTO(AmlCase entity) {
        if ( entity == null ) {
            return null;
        }

        AmlCaseDTO.AmlCaseDTOBuilder amlCaseDTO = AmlCaseDTO.builder();

        amlCaseDTO.amlCaseId( entity.getAmlCaseId() );
        amlCaseDTO.reportingRunId( entity.getReportingRunId() );
        amlCaseDTO.customerId( entity.getCustomerId() );
        amlCaseDTO.transactionId( entity.getTransactionId() );
        amlCaseDTO.amlCaseStatus( entity.getAmlCaseStatus() );
        amlCaseDTO.caseOpenedAt( entity.getCaseOpenedAt() );
        amlCaseDTO.caseClosedAt( entity.getCaseClosedAt() );
        amlCaseDTO.riskLevel( entity.getRiskLevel() );
        amlCaseDTO.notes( entity.getNotes() );
        amlCaseDTO.createdAt( entity.getCreatedAt() );
        amlCaseDTO.updatedAt( entity.getUpdatedAt() );

        return amlCaseDTO.build();
    }

    @Override
    public AmlCase toEntity(AmlCaseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AmlCase.AmlCaseBuilder amlCase = AmlCase.builder();

        amlCase.amlCaseId( dto.getAmlCaseId() );
        amlCase.reportingRunId( dto.getReportingRunId() );
        amlCase.customerId( dto.getCustomerId() );
        amlCase.transactionId( dto.getTransactionId() );
        amlCase.amlCaseStatus( dto.getAmlCaseStatus() );
        amlCase.caseOpenedAt( dto.getCaseOpenedAt() );
        amlCase.caseClosedAt( dto.getCaseClosedAt() );
        amlCase.riskLevel( dto.getRiskLevel() );
        amlCase.notes( dto.getNotes() );
        amlCase.createdAt( dto.getCreatedAt() );
        amlCase.updatedAt( dto.getUpdatedAt() );

        return amlCase.build();
    }
}
