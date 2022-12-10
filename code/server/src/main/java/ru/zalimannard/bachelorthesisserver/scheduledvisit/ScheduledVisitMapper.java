package ru.zalimannard.bachelorthesisserver.scheduledvisit;

public interface ScheduledVisitMapper {
    ScheduledVisit toEntity(ScheduledVisitDto dto);

    ScheduledVisitDto toDto(ScheduledVisit entity);
}
