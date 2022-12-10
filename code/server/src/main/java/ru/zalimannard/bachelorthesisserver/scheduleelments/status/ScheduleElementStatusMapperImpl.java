package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import org.springframework.stereotype.Component;

@Component
public class ScheduleElementStatusMapperImpl implements ScheduleElementStatusMapper {
    @Override
    public ScheduleElementStatus toEntity(ScheduleElementStatusDto dto) {
        return ScheduleElementStatus.builder()
                .id(dto.getId())
                .type(dto.getType())
                .name(dto.getName())
                .build();
    }

    @Override
    public ScheduleElementStatusDto toDto(ScheduleElementStatus entity) {
        return ScheduleElementStatusDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .name(entity.getName())
                .build();
    }
}
