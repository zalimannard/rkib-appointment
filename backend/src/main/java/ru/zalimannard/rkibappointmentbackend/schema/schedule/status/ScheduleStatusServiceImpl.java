package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type.ScheduleStatusType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleStatusServiceImpl implements ScheduleStatusService {

    private final ScheduleStatusMapper mapper;
    private final ScheduleStatusRepository repository;

    @Override
    public ScheduleStatusResponseDto create(ScheduleStatusRequestDto procedureDto) {
        ScheduleStatus scheduleStatusToCreate = mapper.toEntity(procedureDto);
        ScheduleStatus createdScheduleStatus = createEntity(scheduleStatusToCreate);
        return mapper.toDto(createdScheduleStatus);
    }

    @Override
    public ScheduleStatus createEntity(ScheduleStatus scheduleStatus) {
        try {
            return repository.save(scheduleStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sss-01", "Конфликт при добавлении ScheduleStatus в базу данных", e.getMessage());
        }
    }

    @Override
    public ScheduleStatusResponseDto read(String id) {
        ScheduleStatus scheduleStatus = readEntity(id);
        return mapper.toDto(scheduleStatus);
    }

    @Override
    public ScheduleStatus readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("sss-02", "Не найден ScheduleStatus с id=" + id, null));
    }

    @Override
    public List<ScheduleStatusResponseDto> readAll() {
        List<ScheduleStatus> scheduleStatuses = readAllEntities();
        return mapper.toDtoList(scheduleStatuses);
    }

    @Override
    public List<ScheduleStatus> readAllEntities() {
        return repository.findAll();
    }

    @Override
    public ScheduleStatusResponseDto update(String id, ScheduleStatusRequestDto procedureDto) {
        ScheduleStatus scheduleStatusToUpdate = mapper.toEntity(procedureDto);
        scheduleStatusToUpdate.setId(id);
        ScheduleStatus updatedScheduleStatus = updateEntity(scheduleStatusToUpdate);
        return mapper.toDto(updatedScheduleStatus);
    }

    @Override
    public ScheduleStatus updateEntity(ScheduleStatus scheduleStatus) {
        try {
            return repository.save(scheduleStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sss-03", "Конфликт при обновлении ScheduleStatus в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            ScheduleStatus scheduleStatus = readEntity(id);
            repository.delete(scheduleStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sss-04", "Конфликт при удалении ScheduleStatus из базы данных", e.getMessage());
        }
    }

    @PostConstruct
    public void initDefaultStatus() {
        try {
            ScheduleStatus scheduleStatusActive = ScheduleStatus.builder()
                    .name("Активно")
                    .type(ScheduleStatusType.ACTIVE)
                    .build();
            createEntity(scheduleStatusActive);
            ScheduleStatus scheduleStatusPending = ScheduleStatus.builder()
                    .name("Ожидает")
                    .type(ScheduleStatusType.PENDING)
                    .build();
            createEntity(scheduleStatusPending);
            ScheduleStatus scheduleStatusCanceled = ScheduleStatus.builder()
                    .name("Отменено")
                    .type(ScheduleStatusType.CANCELED)
                    .build();
            createEntity(scheduleStatusCanceled);
        } catch (ConflictException ignored) {

        }
    }

}
