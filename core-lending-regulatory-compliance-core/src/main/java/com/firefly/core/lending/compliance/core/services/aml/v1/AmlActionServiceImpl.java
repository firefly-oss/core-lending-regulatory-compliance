package com.firefly.core.lending.compliance.core.services.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.compliance.core.mappers.aml.v1.AmlActionMapper;
import com.firefly.core.lending.compliance.interfaces.dtos.aml.v1.AmlActionDTO;
import com.firefly.core.lending.compliance.models.entities.aml.v1.AmlAction;
import com.firefly.core.lending.compliance.models.repositories.aml.v1.AmlActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class AmlActionServiceImpl implements AmlActionService {

    @Autowired
    private AmlActionRepository repository;

    @Autowired
    private AmlActionMapper mapper;

    @Override
    public Mono<PaginationResponse<AmlActionDTO>> findAll(Long amlCaseId, FilterRequest<AmlActionDTO> filterRequest) {
        filterRequest.getFilters().setAmlCaseId(amlCaseId);
        return FilterUtils.createFilter(
                AmlAction.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<AmlActionDTO> create(Long amlCaseId, AmlActionDTO dto) {
        dto.setAmlCaseId(amlCaseId);
        AmlAction entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlActionDTO> getById(Long amlCaseId, Long amlActionId) {
        return repository.findById(amlActionId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlActionDTO> update(Long amlCaseId, Long amlActionId, AmlActionDTO dto) {
        return repository.findById(amlActionId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .flatMap(existingEntity -> {
                    AmlAction updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setAmlActionId(amlActionId);
                    updatedEntity.setAmlCaseId(amlCaseId);
                    updatedEntity.setCreatedAt(existingEntity.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long amlCaseId, Long amlActionId) {
        return repository.findById(amlActionId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .flatMap(repository::delete);
    }
}