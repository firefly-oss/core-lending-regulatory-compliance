package com.firefly.core.lending.compliance.core.mappers.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRunDTO;
import com.firefly.core.lending.compliance.models.entities.reporting.v1.ReportingRun;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:53+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ReportingRunMapperImpl implements ReportingRunMapper {

    @Override
    public ReportingRunDTO toDTO(ReportingRun entity) {
        if ( entity == null ) {
            return null;
        }

        ReportingRunDTO.ReportingRunDTOBuilder reportingRunDTO = ReportingRunDTO.builder();

        reportingRunDTO.reportingRunId( entity.getReportingRunId() );
        reportingRunDTO.reportType( entity.getReportType() );
        reportingRunDTO.reportingPeriodStart( entity.getReportingPeriodStart() );
        reportingRunDTO.reportingPeriodEnd( entity.getReportingPeriodEnd() );
        reportingRunDTO.runStatus( entity.getRunStatus() );
        reportingRunDTO.remarks( entity.getRemarks() );
        reportingRunDTO.createdAt( entity.getCreatedAt() );
        reportingRunDTO.updatedAt( entity.getUpdatedAt() );

        return reportingRunDTO.build();
    }

    @Override
    public ReportingRun toEntity(ReportingRunDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReportingRun.ReportingRunBuilder reportingRun = ReportingRun.builder();

        reportingRun.reportingRunId( dto.getReportingRunId() );
        reportingRun.reportType( dto.getReportType() );
        reportingRun.reportingPeriodStart( dto.getReportingPeriodStart() );
        reportingRun.reportingPeriodEnd( dto.getReportingPeriodEnd() );
        reportingRun.runStatus( dto.getRunStatus() );
        reportingRun.remarks( dto.getRemarks() );
        reportingRun.createdAt( dto.getCreatedAt() );
        reportingRun.updatedAt( dto.getUpdatedAt() );

        return reportingRun.build();
    }
}
