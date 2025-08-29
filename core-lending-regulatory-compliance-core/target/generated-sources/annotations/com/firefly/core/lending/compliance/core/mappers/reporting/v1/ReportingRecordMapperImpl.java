package com.firefly.core.lending.compliance.core.mappers.reporting.v1;

import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRecordDTO;
import com.firefly.core.lending.compliance.models.entities.reporting.v1.ReportingRecord;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:43:09+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ReportingRecordMapperImpl implements ReportingRecordMapper {

    @Override
    public ReportingRecordDTO toDTO(ReportingRecord entity) {
        if ( entity == null ) {
            return null;
        }

        ReportingRecordDTO.ReportingRecordDTOBuilder reportingRecordDTO = ReportingRecordDTO.builder();

        reportingRecordDTO.reportingRecordId( entity.getReportingRecordId() );
        reportingRecordDTO.reportingRunId( entity.getReportingRunId() );
        reportingRecordDTO.loanServicingCaseId( entity.getLoanServicingCaseId() );
        reportingRecordDTO.customerId( entity.getCustomerId() );
        reportingRecordDTO.transactionId( entity.getTransactionId() );
        reportingRecordDTO.recordType( entity.getRecordType() );
        reportingRecordDTO.reportedAmount( entity.getReportedAmount() );
        reportingRecordDTO.currencyCode( entity.getCurrencyCode() );
        reportingRecordDTO.additionalData( entity.getAdditionalData() );
        reportingRecordDTO.isIncluded( entity.getIsIncluded() );
        reportingRecordDTO.exclusionReason( entity.getExclusionReason() );
        reportingRecordDTO.createdAt( entity.getCreatedAt() );
        reportingRecordDTO.updatedAt( entity.getUpdatedAt() );

        return reportingRecordDTO.build();
    }

    @Override
    public ReportingRecord toEntity(ReportingRecordDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReportingRecord.ReportingRecordBuilder reportingRecord = ReportingRecord.builder();

        reportingRecord.reportingRecordId( dto.getReportingRecordId() );
        reportingRecord.reportingRunId( dto.getReportingRunId() );
        reportingRecord.loanServicingCaseId( dto.getLoanServicingCaseId() );
        reportingRecord.customerId( dto.getCustomerId() );
        reportingRecord.transactionId( dto.getTransactionId() );
        reportingRecord.recordType( dto.getRecordType() );
        reportingRecord.reportedAmount( dto.getReportedAmount() );
        reportingRecord.currencyCode( dto.getCurrencyCode() );
        reportingRecord.additionalData( dto.getAdditionalData() );
        reportingRecord.isIncluded( dto.getIsIncluded() );
        reportingRecord.exclusionReason( dto.getExclusionReason() );
        reportingRecord.createdAt( dto.getCreatedAt() );
        reportingRecord.updatedAt( dto.getUpdatedAt() );

        return reportingRecord.build();
    }
}
