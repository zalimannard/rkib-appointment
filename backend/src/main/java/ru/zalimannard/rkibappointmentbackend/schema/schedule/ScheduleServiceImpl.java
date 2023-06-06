package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.Appointment;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.AppointmentService;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.Employee;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.EmployeeService;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.Procedure;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.ProcedureService;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatus;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatusService;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper mapper;
    private final ScheduleRepository repository;

    private final EmployeeService employeeService;
    private final ProcedureService procedureService;
    private final AppointmentService appointmentService;
    private final ScheduleStatusService scheduleStatusService;

    @Override
    public ScheduleResponseDto create(ScheduleRequestDto scheduleDto) {
        Employee doctor = employeeService.readEntity(scheduleDto.getDoctorId());
        Procedure procedure = procedureService.readEntity(scheduleDto.getProcedureId());
        Appointment appointment = appointmentService.readEntity(scheduleDto.getAppointmentId());
        ScheduleStatus status = scheduleStatusService.readEntity(scheduleDto.getStatusId());

        Schedule scheduleToCreate = mapper.toEntity(scheduleDto, doctor, procedure, appointment, status);
        Schedule createdSchedule = createEntity(scheduleToCreate);
        return mapper.toDto(createdSchedule);
    }

    @Override
    public Schedule createEntity(Schedule schedule) {
        try {
            return repository.save(schedule);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("scs-01", "Конфликт при добавлении Schedule в базу данных", e.getMessage());
        }
    }

    @Override
    public ScheduleResponseDto read(String id) {
        Schedule schedule = readEntity(id);
        return mapper.toDto(schedule);
    }

    @Override
    public Schedule readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("scs-02", "Не найден Schedule с id=" + id, null));
    }

    @Override
    public ScheduleResponseDto update(String id, ScheduleRequestDto scheduleDto) {
        Employee doctor = employeeService.readEntity(scheduleDto.getDoctorId());
        Procedure procedure = procedureService.readEntity(scheduleDto.getProcedureId());
        Appointment appointment = appointmentService.readEntity(scheduleDto.getAppointmentId());
        ScheduleStatus status = scheduleStatusService.readEntity(scheduleDto.getStatusId());

        Schedule scheduleToUpdate = mapper.toEntity(scheduleDto, doctor, procedure, appointment, status);
        scheduleToUpdate.setId(id);
        Schedule updatedSchedule = updateEntity(scheduleToUpdate);
        return mapper.toDto(updatedSchedule);
    }

    @Override
    public Schedule updateEntity(Schedule schedule) {
        try {
            read(schedule.getId());
            return repository.save(schedule);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("scs-03", "Конфликт при обновлении Schedule в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Schedule schedule = readEntity(id);
            repository.delete(schedule);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("scs-04", "Конфликт при удалении Schedule из базы данных", e.getMessage());
        }
    }

}
