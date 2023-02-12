package ru.zalimannard.bachelorthesisserver.institution;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper = Mappers.getMapper(InstitutionMapper.class);

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public InstitutionDto get(int id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Institution", "id", String.valueOf(id)));
        return institutionMapper.toDto(institution);
    }

    @Override
    public List<InstitutionDto> list() {
        List<Institution> institutionList = new ArrayList<>();
        institutionRepository.findAll().forEach(institution -> institutionList.add(institution));
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
    public InstitutionDto delete(int id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Institution", "id", String.valueOf(id)));
        institutionRepository.deleteById(id);
        return institutionMapper.toDto(institution);
    }
}
