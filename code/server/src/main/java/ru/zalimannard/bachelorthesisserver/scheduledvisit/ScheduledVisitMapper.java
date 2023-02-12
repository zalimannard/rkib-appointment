package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ScheduledVisitMapper {

    ScheduledVisit toEntity(ScheduledVisitDto dto);

    ScheduledVisitDto toDto(ScheduledVisit entity);

    List<ScheduledVisit> toEntityList(List<ScheduledVisitDto> dtoList);

    List<ScheduledVisitDto> toDtoList(List<ScheduledVisit> entityList);
}
