package ru.zalimannard.bachelorthesisserver.schema.patient;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PatientMapper {

    public abstract Patient toEntity(PatientDto dto,
                                     @Context MappingType mappingType);

    public abstract PatientDto toDto(Patient entity);

    public abstract List<Patient> toEntityList(List<PatientDto> dtoList,
                                               @Context MappingType mappingType);

    public abstract List<PatientDto> toDtoList(List<Patient> entityList);

}
