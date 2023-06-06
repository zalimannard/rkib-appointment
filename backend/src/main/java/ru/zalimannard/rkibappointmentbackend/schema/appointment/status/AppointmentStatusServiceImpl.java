package ru.zalimannard.rkibappointmentbackend.schema.appointment.status;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentStatusServiceImpl implements AppointmentStatusService {

    private final AppointmentStatusMapper mapper;
    private final AppointmentStatusRepository repository;

    @Override
    public AppointmentStatusResponseDto create(AppointmentStatusRequestDto procedureDto) {
        AppointmentStatus appointmentStatusToCreate = mapper.toEntity(procedureDto);
        AppointmentStatus createdAppointmentStatus = createEntity(appointmentStatusToCreate);
        return mapper.toDto(createdAppointmentStatus);
    }

    @Override
    public AppointmentStatus createEntity(AppointmentStatus appointmentStatus) {
        try {
            return repository.save(appointmentStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("ass-01", "Конфликт при добавлении AppointmentStatus в базу данных", e.getMessage());
        }
    }

    @Override
    public AppointmentStatusResponseDto read(String id) {
        AppointmentStatus appointmentStatus = readEntity(id);
        return mapper.toDto(appointmentStatus);
    }

    @Override
    public AppointmentStatus readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("ass-02", "Не найден AppointmentStatus с id=" + id, null));
    }

    @Override
    public List<AppointmentStatusResponseDto> readAll() {
        List<AppointmentStatus> appointmentStatuses = readAllEntities();
        return mapper.toDtoList(appointmentStatuses);
    }

    @Override
    public List<AppointmentStatus> readAllEntities() {
        return repository.findAll();
    }

    @Override
    public AppointmentStatusResponseDto update(String id, AppointmentStatusRequestDto procedureDto) {
        AppointmentStatus appointmentStatusToUpdate = mapper.toEntity(procedureDto);
        appointmentStatusToUpdate.setId(id);
        AppointmentStatus updatedAppointmentStatus = updateEntity(appointmentStatusToUpdate);
        return mapper.toDto(updatedAppointmentStatus);
    }

    @Override
    public AppointmentStatus updateEntity(AppointmentStatus appointmentStatus) {
        try {
            read(appointmentStatus.getId());
            return repository.save(appointmentStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("ass-03", "Конфликт при обновлении AppointmentStatus в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            AppointmentStatus appointmentStatus = readEntity(id);
            repository.delete(appointmentStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("ass-04", "Конфликт при удалении AppointmentStatus из базы данных", e.getMessage());
        }
    }

}
