package com.catalis.core.lending.compliance.core.services.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.compliance.core.mappers.aml.v1.AmlCaseMapper;
import com.catalis.core.lending.compliance.interfaces.dtos.aml.v1.AmlCaseDTO;
import com.catalis.core.lending.compliance.models.entities.aml.v1.AmlCase;
import com.catalis.core.lending.compliance.models.repositories.aml.v1.AmlCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

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
    public Mono<AmlCaseDTO> getById(Long amlCaseId) {
        return repository.findById(amlCaseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlCaseDTO> update(Long amlCaseId, AmlCaseDTO dto) {
        return repository.findById(amlCaseId)
                .flatMap(existing -> {
                    AmlCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setAmlCaseId(amlCaseId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long amlCaseId) {
        return repository.deleteById(amlCaseId);
    }
}