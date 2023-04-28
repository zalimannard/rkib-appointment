package ru.zalimannard.rkibappointmentbackend.schema.procedures;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureResponseDto;

@Validated
public interface ProcedureService {

    ProcedureResponseDto create(@NotNull @Valid ProcedureRequestDto procedureDto);

    Procedure createEntity(@NotNull Procedure procedure);


    ProcedureResponseDto read(@NotNull String id);

    Procedure readEntity(@NotNull String id);


    ProcedureResponseDto update(@NotNull String id, @NotNull @Valid ProcedureRequestDto procedureDto);

    Procedure updateEntity(@NotNull Procedure procedure);


    void delete(@NotNull String id);

}
