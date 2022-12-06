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
    public InstitutionDto get(int id, boolean expand) {
        Optional<InstitutionEntity> institutionEntityOptional = institutionRepository.findById(id);
        if (institutionEntityOptional.isPresent()) {
            InstitutionEntity institutionEntity = institutionEntityOptional.get();
            return institutionEntity.toDto(expand);
        } else {
            throw new NotFoundException("Учреждение не найдено");
        }
    }

    @Override
    public List<InstitutionDto> getAll(boolean expand) {
        Iterable<InstitutionEntity> institutionEntities = institutionRepository.findAll();
        List<InstitutionDto> institutioDtos = new ArrayList<>();
        institutionEntities.forEach(institutionEntity -> institutioDtos.add(institutionEntity.toDto(expand)));
        return institutioDtos;
    }

    @Override
    public InstitutionDto post(InstitutionEntity institutionEntity) {
        InstitutionEntity createdEntity = institutionRepository.save(institutionEntity);
        return createdEntity.toDto(true);
    }

    @Override
    public InstitutionDto put(InstitutionEntity institutionEntity) {
        if (institutionRepository.existsById(institutionEntity.getId())) {
            institutionRepository.save(institutionEntity);
            return institutionEntity.toDto(true);
        } else {
            throw new NotFoundException("Указанного для изменения учреждения не существует");
        }
    }

    @Override
    public InstitutionDto delete(int id) {
        Optional<InstitutionEntity> institutionEntityOptional = institutionRepository.findById(id);
        if (institutionEntityOptional.isPresent()) {
            institutionRepository.deleteById(id);
            InstitutionEntity institutionEntity = institutionEntityOptional.get();
            return institutionEntity.toDto(false);
        } else {
            throw new NotFoundException("Учреждение не найдено");
        }
    }
}
