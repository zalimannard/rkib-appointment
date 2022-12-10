package ru.zalimannard.bachelorthesisserver.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceMapperImpl implements ServiceMapper {
    @Override
    public Service toEntity(ServiceDto dto) {
        return Service.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public ServiceDto toDto(Service entity) {
        return ServiceDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
