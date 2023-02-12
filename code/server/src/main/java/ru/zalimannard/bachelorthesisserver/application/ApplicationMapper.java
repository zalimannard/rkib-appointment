package ru.zalimannard.bachelorthesisserver.application;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    Application toEntity(ApplicationDto dto);

    ApplicationDto toDto(Application entity);

    List<Application> toEntityList(List<ApplicationDto> dtoList);

    List<ApplicationDto> toDtoList(List<Application> entityList);
}
