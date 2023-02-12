package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ScheduleElementStatusMapper {

    ScheduleElementStatus toEntity(ScheduleElementStatusDto dto);

    ScheduleElementStatusDto toDto(ScheduleElementStatus entity);

    List<ScheduleElementStatus> toEntityList(List<ScheduleElementStatusDto> dtoList);

    List<ScheduleElementStatusDto> toDtoList(List<ScheduleElementStatus> entityList);
}
