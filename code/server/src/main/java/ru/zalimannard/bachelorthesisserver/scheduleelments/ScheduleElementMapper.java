package ru.zalimannard.bachelorthesisserver.scheduleelments;

public interface ScheduleElementMapper {
    ScheduleElement toEntity(ScheduleElementDto dto);

    ScheduleElementDto toDto(ScheduleElement entity);
}
