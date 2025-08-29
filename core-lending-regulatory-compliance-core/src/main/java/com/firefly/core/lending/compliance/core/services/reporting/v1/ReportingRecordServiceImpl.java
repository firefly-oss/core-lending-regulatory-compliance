package com.firefly.core.lending.compliance.core.services.reporting.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.core.mappers.reporting.v1.ReportingRecordMapper;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRecordDTO;
import com.firefly.core.lending.compliance.models.entities.reporting.v1.ReportingRecord;
import com.firefly.core.lending.compliance.models.repositories.reporting.v1.ReportingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ReportingRecordServiceImpl implements ReportingRecordService {

    @Autowired
    private ReportingRecordRepository repository;

    @Autowired
    private ReportingRecordMapper mapper;

    @Override
    public Mono<PaginationResponse<ReportingRecordDTO>> findAll(Long reportingRunId, FilterRequest<ReportingRecordDTO> filterRequest) {
        filterRequest.getFilters().setReportingRunId(reportingRunId);
        return FilterUtils.createFilter(
                ReportingRecord.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ReportingRecordDTO> create(Long reportingRunId, ReportingRecordDTO dto) {
        dto.setReportingRunId(reportingRunId);
        ReportingRecord entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ReportingRecordDTO> getById(Long reportingRunId, Long reportingRecordId) {
        return repository.findById(reportingRecordId)
                .filter(record -> record.getReportingRunId().equals(reportingRunId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ReportingRecordDTO> update(Long reportingRunId, Long reportingRecordId, ReportingRecordDTO dto) {
        return repository.findById(reportingRecordId)
                .filter(record -> record.getReportingRunId().equals(reportingRunId))
                .flatMap(existingRecord -> {
                    ReportingRecord updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setReportingRecordId(existingRecord.getReportingRecordId());
                    updatedEntity.setReportingRunId(existingRecord.getReportingRunId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long reportingRunId, Long reportingRecordId) {
        return repository.findById(reportingRecordId)
                .filter(record -> record.getReportingRunId().equals(reportingRunId))
                .flatMap(repository::delete);
    }
}