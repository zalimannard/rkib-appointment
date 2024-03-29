package ru.zalimannard.rkibappointmentbackend.schema.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionMapper mapper;
    private final InstitutionRepository repository;

    @Override
    public InstitutionResponseDto create(InstitutionRequestDto institutionDto) {
        Institution institutionToCreate = mapper.toEntity(institutionDto);
        Institution createdInstitution = createEntity(institutionToCreate);
        return mapper.toDto(createdInstitution);
    }

    @Override
    public Institution createEntity(Institution institution) {
        try {
            return repository.save(institution);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("ins-01", "Конфликт при добавлении Institution в базу данных", e.getMessage());
        }
    }

    @Override
    public InstitutionResponseDto read(String id) {
        Institution institution = readEntity(id);
        return mapper.toDto(institution);
    }

    @Override
    public Institution readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("ins-02", "Не найден Institution с id=" + id, null));
    }

    @Override
    public List<InstitutionResponseDto> readAll() {
        List<Institution> institutions = readAllEntities();
        return mapper.toDtoList(institutions);
    }

    @Override
    public List<Institution> readAllEntities() {
        return repository.findAll();
    }

    @Override
    public InstitutionResponseDto update(String id, InstitutionRequestDto institutionDto) {
        Institution institutionToUpdate = mapper.toEntity(institutionDto);
        institutionToUpdate.setId(id);
        Institution updatedInstitution = updateEntity(institutionToUpdate);
        return mapper.toDto(updatedInstitution);
    }

    @Override
    public Institution updateEntity(Institution institution) {
        try {
            read(institution.getId());
            return repository.save(institution);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("ins-03", "Конфликт при обновлении Institution в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Institution institution = readEntity(id);
            repository.delete(institution);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("ins-04", "Конфликт при удалении Institution из базы данных", e.getMessage());
        }
    }

}
