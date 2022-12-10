package ru.zalimannard.bachelorthesisserver.service;

public interface ServiceMapper {
    Service toEntity(ServiceDto dto);

    ServiceDto toDto(Service entity);
}
