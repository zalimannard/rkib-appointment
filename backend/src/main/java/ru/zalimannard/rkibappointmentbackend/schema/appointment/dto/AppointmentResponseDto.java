package ru.zalimannard.rkibappointmentbackend.schema.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;


@Value
@Builder(toBuilder = true)
public class AppointmentResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("patient")
    PatientResponseDto patient;

    @JsonProperty("institution")
    InstitutionResponseDto institution;

    @JsonProperty("status")
    AppointmentStatusResponseDto status;

    @JsonProperty("doctorNote")
    String doctorNote;

    @JsonProperty("diagnosis")
    String diagnosis;

    @JsonProperty("commentary")
    String commentary;

}
