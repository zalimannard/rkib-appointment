package ru.zalimannard.bachelorthesisserver.schema.application.status;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ApplicationStatusMapper {

    public abstract ApplicationStatus toEntity(ApplicationStatusDto dto,
                                               @Context MappingType mappingType);

    public abstract ApplicationStatusDto toDto(ApplicationStatus entity);

    public abstract List<ApplicationStatus> toEntityList(List<ApplicationStatusDto> dtoList,
                                                         @Context MappingType mappingType);

    public abstract List<ApplicationStatusDto> toDtoList(List<ApplicationStatus> entityList);

}
