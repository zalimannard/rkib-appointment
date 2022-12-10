package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

public interface UnscheduledVisitMapper {
    UnscheduledVisit toEntity(UnscheduledVisitDto dto);

    UnscheduledVisitDto toDto(UnscheduledVisit entity);
}
