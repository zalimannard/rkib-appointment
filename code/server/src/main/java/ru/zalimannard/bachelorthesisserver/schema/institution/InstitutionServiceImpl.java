package ru.zalimannard.bachelorthesisserver.schema.institution;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper = Mappers.getMapper(InstitutionMapper.class);

    @Override
    public InstitutionDto get(String id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Institution", "id", id));
        return institutionMapper.toDto(institution);
    }

    @Override
    public List<InstitutionDto> list(InstitutionDto exampleInstitutionDto) {
        Institution exampleInstitution = institutionMapper.toEntity(exampleInstitutionDto);
        List<Institution> institutionList = new ArrayList<>(institutionRepository.findAll(Example.of(exampleInstitution)));
        return institutionMapper.toDtoList(institutionList);
    }

    @Override
    public InstitutionDto create(InstitutionDto institutionDto) {
        Institution institutionRequest = institutionMapper.toEntity(institutionDto);
        Institution institutionResponse = institutionRepository.save(institutionRequest);
        return institutionMapper.toDto(institutionResponse);
    }

    @Override
    public InstitutionDto update(InstitutionDto institutionDto) {
        Institution institutionRequest = institutionMapper.toEntity(institutionDto);
        if (institutionRepository.existsById(institutionRequest.getId())) {
            Institution institutionResponse = institutionRepository.save(institutionRequest);
            return institutionMapper.toDto(institutionResponse);
        } else {
            throw new NotFoundException("Institution", "id", String.valueOf(institutionRequest.getId()));
        }
    }

    @Override
    public InstitutionDto delete(String id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Institution", "id", id));
        institutionRepository.deleteById(id);
        return institutionMapper.toDto(institution);
    }
}
