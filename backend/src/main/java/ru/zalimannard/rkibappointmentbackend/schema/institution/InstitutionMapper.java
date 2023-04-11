package ru.zalimannard.rkibappointmentbackend.schema.institution;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    Institution toEntity(InstitutionDto dto);

    InstitutionDto toDto(Institution entity);

    List<Institution> toEntityList(List<InstitutionDto> dto);

    List<InstitutionDto> toDtoList(List<Institution> entity);

}
