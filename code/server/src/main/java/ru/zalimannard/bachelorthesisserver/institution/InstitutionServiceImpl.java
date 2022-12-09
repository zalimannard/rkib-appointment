package ru.zalimannard.bachelorthesisserver.institution;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionMapper institutionMapper;
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionMapper institutionMapper, InstitutionRepository institutionRepository) {
        this.institutionMapper = institutionMapper;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public InstitutionDto get(int id) {
        Optional<Institution> institutionOptional = institutionRepository.findById(id);
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            return institutionMapper.toDto(institution);
        } else {
            throw new NotFoundException("Учреждение с id=" + id + " не найдено");
        }
    }

    @Override
    public List<InstitutionDto> getAll() {
        Iterable<Institution> institutionEntities = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = new ArrayList<>();
        institutionEntities.forEach(institutionEntity -> institutionDtos.add(institutionMapper.toDto(institutionEntity)));
        return institutionDtos;
    }

    @Override
    public InstitutionDto post(InstitutionDto institutionDto) {
        Institution institutionToAdd = institutionMapper.toEntity(institutionDto);
        Institution createdEntity = institutionRepository.save(institutionToAdd);
        return institutionMapper.toDto(createdEntity);
    }

    @Override
    public InstitutionDto put(InstitutionDto institutionDto) {
        if (institutionRepository.existsById(institutionDto.getId())) {
            Institution institution = institutionMapper.toEntity(institutionDto);
            institutionRepository.save(institution);
            return get(institutionDto.getId());
        } else {
            throw new NotFoundException("Учреждение с id=" + institutionDto.getId()+ " не найдено. Ничего не изменено.");
        }
    }

    @Override
    public InstitutionDto delete(int id) {
        InstitutionDto institutionDto = get(id);
        institutionRepository.deleteById(id);
        return institutionDto;
    }
}
