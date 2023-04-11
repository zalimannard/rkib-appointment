package ru.zalimannard.rkibappointmentbackend.schema.institution;

public interface InstitutionMapper {

    Institution toEntity(InstitutionDto dto);

    InstitutionDto toDto(Institution entity);

}
