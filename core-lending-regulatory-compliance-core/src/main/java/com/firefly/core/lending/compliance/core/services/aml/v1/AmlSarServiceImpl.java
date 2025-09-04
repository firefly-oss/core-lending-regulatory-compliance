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
import com.firefly.core.lending.compliance.core.mappers.aml.v1.AmlSarMapper;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlSarDTO;
import com.firefly.core.lending.compliance.models.entities.aml.v1.AmlSar;
import com.firefly.core.lending.compliance.models.repositories.aml.v1.AmlSarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class AmlSarServiceImpl implements AmlSarService {

    @Autowired
    private AmlSarRepository repository;

    @Autowired
    private AmlSarMapper mapper;

    @Override
    public Mono<PaginationResponse<AmlSarDTO>> findAll(UUID amlCaseId, FilterRequest<AmlSarDTO> filterRequest) {
        filterRequest.getFilters().setAmlCaseId(amlCaseId);
        return FilterUtils.createFilter(
                AmlSar.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<AmlSarDTO> create(UUID amlCaseId, AmlSarDTO dto) {
        dto.setAmlCaseId(amlCaseId);
        AmlSar entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlSarDTO> getById(UUID amlCaseId, UUID amlSarId) {
        return repository.findById(amlSarId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlSarDTO> update(UUID amlCaseId, UUID amlSarId, AmlSarDTO dto) {
        return repository.findById(amlSarId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .flatMap(existingEntity -> {
                    AmlSar updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setAmlSarId(existingEntity.getAmlSarId());
                    updatedEntity.setAmlCaseId(existingEntity.getAmlCaseId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID amlCaseId, UUID amlSarId) {
        return repository.findById(amlSarId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .flatMap(repository::delete);
    }
}
