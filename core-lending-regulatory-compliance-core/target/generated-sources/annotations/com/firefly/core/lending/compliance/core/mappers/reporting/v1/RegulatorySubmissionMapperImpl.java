package com.firefly.core.lending.compliance.core.mappers.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.RegulatorySubmissionDTO;
import com.firefly.core.lending.compliance.models.entities.reporting.v1.RegulatorySubmission;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:43:09+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class RegulatorySubmissionMapperImpl implements RegulatorySubmissionMapper {

    @Override
    public RegulatorySubmissionDTO toDTO(RegulatorySubmission entity) {
        if ( entity == null ) {
            return null;
        }

        RegulatorySubmissionDTO.RegulatorySubmissionDTOBuilder regulatorySubmissionDTO = RegulatorySubmissionDTO.builder();

        regulatorySubmissionDTO.regulatorySubmissionId( entity.getRegulatorySubmissionId() );
        regulatorySubmissionDTO.reportingRunId( entity.getReportingRunId() );
        regulatorySubmissionDTO.submissionRef( entity.getSubmissionRef() );
        regulatorySubmissionDTO.submissionDate( entity.getSubmissionDate() );
        regulatorySubmissionDTO.submissionStatus( entity.getSubmissionStatus() );
        regulatorySubmissionDTO.responseDetails( entity.getResponseDetails() );
        regulatorySubmissionDTO.createdAt( entity.getCreatedAt() );
        regulatorySubmissionDTO.updatedAt( entity.getUpdatedAt() );

        return regulatorySubmissionDTO.build();
    }

    @Override
    public RegulatorySubmission toEntity(RegulatorySubmissionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RegulatorySubmission.RegulatorySubmissionBuilder regulatorySubmission = RegulatorySubmission.builder();

        regulatorySubmission.regulatorySubmissionId( dto.getRegulatorySubmissionId() );
        regulatorySubmission.reportingRunId( dto.getReportingRunId() );
        regulatorySubmission.submissionRef( dto.getSubmissionRef() );
        regulatorySubmission.submissionDate( dto.getSubmissionDate() );
        regulatorySubmission.submissionStatus( dto.getSubmissionStatus() );
        regulatorySubmission.responseDetails( dto.getResponseDetails() );
        regulatorySubmission.createdAt( dto.getCreatedAt() );
        regulatorySubmission.updatedAt( dto.getUpdatedAt() );

        return regulatorySubmission.build();
    }
}
