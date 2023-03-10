package ru.zalimannard.bachelorthesisserver.schema.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.DataIntegrityViolationExceptionHttp;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundExceptionHttp;
import ru.zalimannard.bachelorthesisserver.utils.Utils;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;

    @Override
    public InstitutionDto create(InstitutionDto institutionDto) {
        try {
            Institution institutionRequest = institutionMapper.toEntity(institutionDto, MappingType.DEFAULT);
            Institution institutionResponse = institutionRepository.save(institutionRequest);
            return institutionMapper.toDto(institutionResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.institution}");
        }
    }

    @Override
    public InstitutionDto read(String id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHttp("${application.entityNames.institution}", id));
        return institutionMapper.toDto(institution);
    }

    @Override
    public List<InstitutionDto> search(InstitutionDto filterInstitutionDto, int pageNo, int pageSize, String[] sort) {
        Institution filterInstitution = institutionMapper.toEntity(filterInstitutionDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<Institution> institutionList = institutionRepository.search(filterInstitution.getName(), pageable);
        return institutionMapper.toDtoList(institutionList);
    }

    @Override
    public InstitutionDto update(String id, InstitutionDto institutionDto) {
        read(id);
        try {
            Institution institutionRequest = institutionMapper.toEntity(institutionDto, MappingType.DEFAULT);
            institutionRequest.setId(id);
            Institution institutionResponse = institutionRepository.save(institutionRequest);
            return institutionMapper.toDto(institutionResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.institution}");
        }
    }

    @Override
    public InstitutionDto delete(String id) {
        try {
            InstitutionDto institutionDto = read(id);
            institutionRepository.deleteById(id);
            return institutionDto;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.institution}");
        }
    }

}
