package ru.zalimannard.rkibappointmentbackend.schema.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "sendingInstitution", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract Application toEntity(ApplicationDto dto);

    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "sendingInstitutionId", ignore = true)
    @Mapping(target = "statusId", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "sendingInstitution", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract ApplicationDto toDto(Application entity);

    public abstract List<Application> toEntityList(List<ApplicationDto> dto);

    public abstract List<ApplicationDto> toDtoList(List<Application> entity);

}
