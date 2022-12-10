package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

public interface ScheduleElementStatusMapper {
    ScheduleElementStatus toEntity(ScheduleElementStatusDto dto);

    ScheduleElementStatusDto toDto(ScheduleElementStatus entity);
}
