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


package com.firefly.core.lending.compliance.core.services.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.core.mappers.aml.v1.AmlCaseMapper;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlCaseDTO;
import com.firefly.core.lending.compliance.models.entities.aml.v1.AmlCase;
import com.firefly.core.lending.compliance.models.repositories.aml.v1.AmlCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class AmlCaseServiceImpl implements AmlCaseService {

    @Autowired
    private AmlCaseRepository repository;

    @Autowired
    private AmlCaseMapper mapper;

    @Override
    public Mono<PaginationResponse<AmlCaseDTO>> findAll(FilterRequest<AmlCaseDTO> filterRequest) {
        return FilterUtils.createFilter(
                AmlCase.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<AmlCaseDTO> create(AmlCaseDTO dto) {
        AmlCase entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlCaseDTO> getById(UUID amlCaseId) {
        return repository.findById(amlCaseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlCaseDTO> update(UUID amlCaseId, AmlCaseDTO dto) {
        return repository.findById(amlCaseId)
                .flatMap(existing -> {
                    AmlCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setAmlCaseId(amlCaseId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID amlCaseId) {
        return repository.deleteById(amlCaseId);
    }
}