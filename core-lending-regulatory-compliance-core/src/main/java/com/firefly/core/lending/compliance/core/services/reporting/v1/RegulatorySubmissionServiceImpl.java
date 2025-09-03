package com.firefly.core.lending.compliance.core.services.reporting.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.core.mappers.reporting.v1.RegulatorySubmissionMapper;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.RegulatorySubmissionDTO;
import com.firefly.core.lending.compliance.models.entities.reporting.v1.RegulatorySubmission;
import com.firefly.core.lending.compliance.models.repositories.reporting.v1.RegulatorySubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class RegulatorySubmissionServiceImpl implements RegulatorySubmissionService {

    @Autowired
    private RegulatorySubmissionRepository repository;

    @Autowired
    private RegulatorySubmissionMapper mapper;

    @Override
    public Mono<PaginationResponse<RegulatorySubmissionDTO>> findAll(UUID reportingRunId, FilterRequest<RegulatorySubmissionDTO> filterRequest) {
        filterRequest.getFilters().setReportingRunId(reportingRunId);
        return FilterUtils.createFilter(
                RegulatorySubmission.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<RegulatorySubmissionDTO> create(UUID reportingRunId, RegulatorySubmissionDTO dto) {
        dto.setReportingRunId(reportingRunId);
        RegulatorySubmission entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .map(e -> {
                    e.setCreatedAt(java.time.LocalDateTime.now());
                    e.setUpdatedAt(java.time.LocalDateTime.now());
                    return e;
                })
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatorySubmissionDTO> getById(UUID reportingRunId, UUID regulatorySubmissionId) {
        return repository.findById(regulatorySubmissionId)
                .filter(entity -> entity.getReportingRunId().equals(reportingRunId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatorySubmissionDTO> update(UUID reportingRunId, UUID regulatorySubmissionId, RegulatorySubmissionDTO dto) {
        return repository.findById(regulatorySubmissionId)
                .filter(entity -> entity.getReportingRunId().equals(reportingRunId))
                .flatMap(existing -> {
                    RegulatorySubmission updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setRegulatorySubmissionId(regulatorySubmissionId);
                    updatedEntity.setReportingRunId(reportingRunId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(java.time.LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID reportingRunId, UUID regulatorySubmissionId) {
        return repository.findById(regulatorySubmissionId)
                .filter(entity -> entity.getReportingRunId().equals(reportingRunId))
                .flatMap(repository::delete);
    }
}