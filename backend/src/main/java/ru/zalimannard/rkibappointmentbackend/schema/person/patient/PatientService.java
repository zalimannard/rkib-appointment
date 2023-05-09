package ru.zalimannard.rkibappointmentbackend.schema.person.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;

import java.util.List;

@Validated
public interface PatientService {

    PatientResponseDto create(@NotNull @Valid PatientRequestDto patientDto);

    Patient createEntity(@NotNull Patient patient);


    PatientResponseDto read(@NotNull String id);

    Patient readEntity(@NotNull String id);

    List<PatientResponseDto> readAll();

    List<Patient> readAllEntities();


    PatientResponseDto update(@NotNull String id, @NotNull @Valid PatientRequestDto patientDto);

    Patient updateEntity(@NotNull Patient patient);


    void delete(@NotNull String id);

}
