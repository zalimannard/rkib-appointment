package ru.zalimannard.rkibappointmentbackend.schema.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusResponseDto;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class ScheduleResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("doctor")
    EmployeeResponseDto doctor;

    @JsonProperty("procedure")
    ProcedureResponseDto procedure;

    @JsonProperty("appointment")
    AppointmentResponseDto appointment;

    @JsonProperty("status")
    ScheduleStatusResponseDto status;

    @JsonProperty("appointmentTime")
    LocalDate appointmentTime;

    @JsonProperty("commentary")
    String commentary;

}
