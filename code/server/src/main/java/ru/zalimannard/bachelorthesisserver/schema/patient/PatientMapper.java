package ru.zalimannard.bachelorthesisserver.schema.patient;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PatientMapper {

    Patient toEntity(PatientDto dto);

    PatientDto toDto(Patient entity);

    List<Patient> toEntityList(List<PatientDto> dtoList);

    List<PatientDto> toDtoList(List<Patient> entityList);
}
