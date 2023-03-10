package ru.zalimannard.bachelorthesisserver.schema.institution;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InstitutionMapper {

    Institution toEntity(InstitutionDto dto);

    InstitutionDto toDto(Institution entity);

    List<Institution> toEntityList(List<InstitutionDto> dtoList);

    List<InstitutionDto> toDtoList(List<Institution> entityList);
}
