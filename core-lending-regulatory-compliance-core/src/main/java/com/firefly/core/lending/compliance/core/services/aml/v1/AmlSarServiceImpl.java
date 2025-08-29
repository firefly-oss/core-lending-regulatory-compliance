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

@Service
@Transactional
public class AmlSarServiceImpl implements AmlSarService {

    @Autowired
    private AmlSarRepository repository;

    @Autowired
    private AmlSarMapper mapper;

    @Override
    public Mono<PaginationResponse<AmlSarDTO>> findAll(Long amlCaseId, FilterRequest<AmlSarDTO> filterRequest) {
        filterRequest.getFilters().setAmlCaseId(amlCaseId);
        return FilterUtils.createFilter(
                AmlSar.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<AmlSarDTO> create(Long amlCaseId, AmlSarDTO dto) {
        dto.setAmlCaseId(amlCaseId);
        AmlSar entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlSarDTO> getById(Long amlCaseId, Long amlSarId) {
        return repository.findById(amlSarId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlSarDTO> update(Long amlCaseId, Long amlSarId, AmlSarDTO dto) {
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
    public Mono<Void> delete(Long amlCaseId, Long amlSarId) {
        return repository.findById(amlSarId)
                .filter(entity -> entity.getAmlCaseId().equals(amlCaseId))
                .flatMap(repository::delete);
    }
}
