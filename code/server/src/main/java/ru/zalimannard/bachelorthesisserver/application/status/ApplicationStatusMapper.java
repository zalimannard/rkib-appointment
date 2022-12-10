package ru.zalimannard.bachelorthesisserver.application.status;

public interface ApplicationStatusMapper {
    ApplicationStatus toEntity(ApplicationStatusDto dto);

    ApplicationStatusDto toDto(ApplicationStatus entity);
}
