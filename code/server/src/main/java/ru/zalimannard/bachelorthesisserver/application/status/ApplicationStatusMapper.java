package ru.zalimannard.bachelorthesisserver.application.status;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ApplicationStatusMapper {

    ApplicationStatus toEntity(ApplicationStatusDto dto);

    ApplicationStatusDto toDto(ApplicationStatus entity);

    List<ApplicationStatus> toEntityList(List<ApplicationStatusDto> dtoList);

    List<ApplicationStatusDto> toDtoList(List<ApplicationStatus> entityList);
}
