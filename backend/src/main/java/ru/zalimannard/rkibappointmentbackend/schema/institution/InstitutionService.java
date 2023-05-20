package ru.zalimannard.rkibappointmentbackend.schema.institution;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionResponseDto;

import java.util.List;

@Validated
public interface InstitutionService {

    InstitutionResponseDto create(@NotNull @Valid InstitutionRequestDto institutionDto);

    Institution createEntity(@NotNull Institution institution);


    InstitutionResponseDto read(@NotNull String id);

    Institution readEntity(@NotNull String id);

    List<InstitutionResponseDto> readAll();

    List<Institution> readAllEntities();


    InstitutionResponseDto update(@NotNull String id, @NotNull @Valid InstitutionRequestDto institutionDto);

    Institution updateEntity(@NotNull Institution institution);


    void delete(@NotNull String id);

}
