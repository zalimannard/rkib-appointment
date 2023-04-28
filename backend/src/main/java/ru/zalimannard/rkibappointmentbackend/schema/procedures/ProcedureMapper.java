package ru.zalimannard.rkibappointmentbackend.schema.procedures;

import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureResponseDto;

@Component
public class ProcedureMapper {

    public Procedure toEntity(ProcedureRequestDto procedureRequestDto) {
        return Procedure.builder()
                .name(procedureRequestDto.getName())
                .build();
    }

    public ProcedureResponseDto toDto(Procedure procedure) {
        return ProcedureResponseDto.builder()
                .id(procedure.getId())
                .name(procedure.getName())
                .build();
    }

}
