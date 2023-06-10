package ru.zalimannard.rkibappointmentbackend.schema.appointment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentResponseDto;

import java.util.List;

@Validated
public interface AppointmentService {

    AppointmentResponseDto create(@NotNull @Valid AppointmentRequestDto appointmentDto);

    Appointment createEntity(@NotNull Appointment appointment);


    AppointmentResponseDto read(@NotNull String id);

    Appointment readEntity(@NotNull String id);

    List<AppointmentResponseDto> readAll();

    List<Appointment> readAllEntities();


    AppointmentResponseDto update(@NotNull String id, @NotNull @Valid AppointmentRequestDto appointmentDto);

    Appointment updateEntity(@NotNull Appointment appointment);


    void delete(@NotNull String id);

}
