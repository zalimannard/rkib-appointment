package ru.zalimannard.bachelorthesisserver.institution;

public interface InstitutionMapper {
    Institution toEntity(InstitutionDto dto);

    InstitutionDto toDto(Institution entity);
}
