package ru.zalimannard.bachelorthesisserver.schema.institution;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class InstitutionMapper {

    public abstract Institution toEntity(InstitutionDto dto,
                                         @Context MappingType mappingType);

    public abstract InstitutionDto toDto(Institution entity);

    public abstract List<Institution> toEntityList(List<InstitutionDto> dtoList,
                                                   @Context MappingType mappingType);

    public abstract List<InstitutionDto> toDtoList(List<Institution> entityList);

}
