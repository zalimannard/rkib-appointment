package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ScheduleStatusMapper {

    ScheduleStatus toEntity(ScheduleStatusDto dto);

    ScheduleStatusDto toDto(ScheduleStatus entity);

    List<ScheduleStatus> toEntityList(List<ScheduleStatusDto> dtoList);

    List<ScheduleStatusDto> toDtoList(List<ScheduleStatus> entityList);

}
