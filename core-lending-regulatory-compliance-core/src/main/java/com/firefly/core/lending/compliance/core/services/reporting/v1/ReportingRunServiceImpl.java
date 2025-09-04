/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.compliance.core.services.reporting.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.core.mappers.reporting.v1.ReportingRunMapper;
import com.firefly.core.lending.compliance.interfaces.dtos.reporting.v1.ReportingRunDTO;
import com.firefly.core.lending.compliance.models.entities.reporting.v1.ReportingRun;
import com.firefly.core.lending.compliance.models.repositories.reporting.v1.ReportingRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
    public Mono<ReportingRunDTO> getById(UUID reportingRunId) {
        return Mono.from(repository.findById(reportingRunId))
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<ReportingRunDTO> update(UUID reportingRunId, ReportingRunDTO dto) {
        return Mono.from(repository.findById(reportingRunId))
                .flatMap(existing -> {
                    ReportingRun updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setReportingRunId(existing.getReportingRunId());
                    return Mono.from(repository.save(updatedEntity))
                            .map(mapper::toDTO);
                });
    }

    @Override
    public Mono<Void> delete(UUID reportingRunId) {
        return Mono.from(repository.findById(reportingRunId))
                .flatMap(existing -> Mono.from(repository.delete(existing)))
                .then();
    }
}
