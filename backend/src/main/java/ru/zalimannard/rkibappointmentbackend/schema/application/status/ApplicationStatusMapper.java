package ru.zalimannard.rkibappointmentbackend.schema.application.status;

public interface ApplicationStatusMapper {

    ApplicationStatus toEntity(ApplicationStatusDto dto);

    ApplicationStatusDto toDto(ApplicationStatus entity);

}
