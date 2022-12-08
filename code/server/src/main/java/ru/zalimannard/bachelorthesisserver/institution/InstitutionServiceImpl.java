package ru.zalimannard.bachelorthesisserver.institution;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public InstitutionDto get(int id) {
        Optional<Institution> institutionOptional = institutionRepository.findById(id);
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            return institution.toDto();
        } else {
            throw new NotFoundException("Учреждение не найдено");
        }
    }

    @Override
    public List<InstitutionDto> getAll() {
        Iterable<Institution> institutionEntities = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = new ArrayList<>();
        institutionEntities.forEach(institutionEntity -> institutionDtos.add(institutionEntity.toDto()));
        return institutionDtos;
    }

    @Override
    public InstitutionDto post(InstitutionDto institutionDto) {
        Institution institutionToAdd = institutionDto.toEntity();
        Institution createdEntity = institutionRepository.save(institutionToAdd);
        return createdEntity.toDto();
    }

    @Override
    public InstitutionDto put(InstitutionDto institutionDto) {
        if (institutionRepository.existsById(institutionDto.getId())) {
            Institution institution = institutionDto.toEntity();
            institutionRepository.save(institution);
            return get(institutionDto.getId());
        } else {
            throw new NotFoundException("Изменяемого учреждения не существует");
        }
    }

    @Override
    public InstitutionDto delete(int id) {
        InstitutionDto institutionDto = get(id);
        institutionRepository.deleteById(id);
        return institutionDto;
    }
}
