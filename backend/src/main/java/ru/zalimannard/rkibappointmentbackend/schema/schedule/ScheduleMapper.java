package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.Appointment;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.AppointmentMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.Employee;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.EmployeeMapper;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.Procedure;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.ProcedureMapper;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatus;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatusMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final EmployeeMapper employeeMapper;
    private final ProcedureMapper procedureMapper;
    private final AppointmentMapper appointmentMapper;
    private final ScheduleStatusMapper scheduleStatusMapper;


    public Schedule toEntity(ScheduleRequestDto scheduleRequestDto,
                             Employee employee,
                             Procedure procedure,
                             Appointment appointment,
                             ScheduleStatus status) {
        return Schedule.builder()
                .doctor(employee)
                .procedure(procedure)
                .appointment(appointment)
                .status(status)
                .appointmentTime(scheduleRequestDto.getAppointmentTime())
                .commentary(scheduleRequestDto.getCommentary())
                .build();
    }

    public ScheduleResponseDto toDto(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .doctor(employeeMapper.toDto(schedule.getDoctor()))
                .procedure(procedureMapper.toDto(schedule.getProcedure()))
                .appointment(appointmentMapper.toDto(schedule.getAppointment()))
                .status(scheduleStatusMapper.toDto(schedule.getStatus()))
                .appointmentTime(schedule.getAppointmentTime())
                .commentary(schedule.getCommentary())
                .build();
    }

    public List<ScheduleResponseDto> toDtoList(List<Schedule> schedules) {
        return schedules.stream().map(this::toDto).toList();
    }

}
