package ru.zalimannard.rkibappointmentbackend.schema.procedures;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureMapper mapper;
    private final ProcedureRepository repository;

    @Override
    public ProcedureResponseDto create(ProcedureRequestDto procedureDto) {
        Procedure procedureToCreate = mapper.toEntity(procedureDto);
        Procedure createdProcedure = createEntity(procedureToCreate);
        return mapper.toDto(createdProcedure);
    }

    @Override
    public Procedure createEntity(Procedure procedure) {
        try {
            return repository.save(procedure);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("prs-01", "Конфликт при добавлении Procedure в базу данных", e.getMessage());
        }
    }

    @Override
    public ProcedureResponseDto read(String id) {
        Procedure procedure = readEntity(id);
        return mapper.toDto(procedure);
    }

    @Override
    public Procedure readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("prs-02", "Не найден Procedure с id=" + id, null));
    }

    @Override
    public List<ProcedureResponseDto> readAll() {
        List<Procedure> procedures = readAllEntities();
        return mapper.toDtoList(procedures);
    }

    @Override
    public List<Procedure> readAllEntities() {
        return repository.findAll();
    }

    @Override
    public ProcedureResponseDto update(String id, ProcedureRequestDto procedureDto) {
        Procedure procedureToUpdate = mapper.toEntity(procedureDto);
        procedureToUpdate.setId(id);
        Procedure updatedProcedure = updateEntity(procedureToUpdate);
        return mapper.toDto(updatedProcedure);
    }

    @Override
    public Procedure updateEntity(Procedure procedure) {
        try {
            return repository.save(procedure);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("prs-03", "Конфликт при обновлении Procedure в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Procedure procedure = readEntity(id);
            repository.delete(procedure);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("prs-04", "Конфликт при удалении Procedure из базы данных", e.getMessage());
        }
    }

}
