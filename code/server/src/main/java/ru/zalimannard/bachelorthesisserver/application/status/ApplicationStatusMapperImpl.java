package ru.zalimannard.bachelorthesisserver.application.status;

import org.springframework.stereotype.Component;

@Component
public class ApplicationStatusMapperImpl implements ApplicationStatusMapper {
    @Override
    public ApplicationStatus toEntity(ApplicationStatusDto dto) {
        return ApplicationStatus.builder()
                .id(dto.getId())
                .type(dto.getType())
                .name(dto.getName())
                .build();
    }

    @Override
    public ApplicationStatusDto toDto(ApplicationStatus entity) {
        return ApplicationStatusDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
