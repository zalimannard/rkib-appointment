package ru.zalimannard.bachelorthesisserver.patient;

public interface PatientMapper {
    Patient toEntity(PatientDto dto);

    PatientDto toDto(Patient entity);
}
