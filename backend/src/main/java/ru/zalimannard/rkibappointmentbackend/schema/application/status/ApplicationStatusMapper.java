package ru.zalimannard.rkibappointmentbackend.schema.application.status;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationStatusMapper {

    ApplicationStatus toEntity(ApplicationStatusDto dto);

    ApplicationStatusDto toDto(ApplicationStatus entity);

    List<ApplicationStatus> toEntityList(List<ApplicationStatusDto> dto);

    List<ApplicationStatusDto> toDtoList(List<ApplicationStatus> entity);

}
