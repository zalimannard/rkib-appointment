package ru.zalimannard.rkibappointmentbackend.schema.appointment.status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusResponseDto;

import java.util.List;

@Validated
public interface AppointmentStatusService {

    AppointmentStatusResponseDto create(@NotNull @Valid AppointmentStatusRequestDto procedureDto);

    AppointmentStatus createEntity(@NotNull AppointmentStatus appointmentStatus);


    AppointmentStatusResponseDto read(@NotNull String id);

    AppointmentStatus readEntity(@NotNull String id);

    List<AppointmentStatusResponseDto> readAll();

    List<AppointmentStatus> readAllEntities();


    AppointmentStatusResponseDto update(@NotNull String id, @NotNull @Valid AppointmentStatusRequestDto procedureDto);

    AppointmentStatus updateEntity(@NotNull AppointmentStatus appointmentStatus);


    void delete(@NotNull String id);

}
