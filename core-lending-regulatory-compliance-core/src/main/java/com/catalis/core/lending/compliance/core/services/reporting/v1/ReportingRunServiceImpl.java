package com.catalis.core.lending.compliance.core.services.reporting.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.compliance.core.mappers.reporting.v1.ReportingRunMapper;
import com.catalis.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRunDTO;
import com.catalis.core.lending.compliance.models.entities.reporting.v1.ReportingRun;
import com.catalis.core.lending.compliance.models.repositories.reporting.v1.ReportingRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ReportingRunServiceImpl implements ReportingRunService {

    @Autowired
    private ReportingRunRepository repository;

    @Autowired
    private ReportingRunMapper mapper;

    @Override
    public Mono<PaginationResponse<ReportingRunDTO>> findAll(FilterRequest<ReportingRunDTO> filterRequest) {
        return FilterUtils.createFilter(
                ReportingRun.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ReportingRunDTO> create(ReportingRunDTO dto) {
        ReportingRun entity = mapper.toEntity(dto);
        return Mono.from(repository.save(entity)).map(mapper::toDTO);
    }

    @Override
    public Mono<ReportingRunDTO> getById(Long reportingRunId) {
        return Mono.from(repository.findById(reportingRunId))
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<ReportingRunDTO> update(Long reportingRunId, ReportingRunDTO dto) {
        return Mono.from(repository.findById(reportingRunId))
                .flatMap(existing -> {
                    ReportingRun updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setReportingRunId(existing.getReportingRunId());
                    return Mono.from(repository.save(updatedEntity))
                            .map(mapper::toDTO);
                });
    }

    @Override
    public Mono<Void> delete(Long reportingRunId) {
        return Mono.from(repository.findById(reportingRunId))
                .flatMap(existing -> Mono.from(repository.delete(existing)))
                .then();
    }
}
