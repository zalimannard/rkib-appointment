package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ScheduleElementMapper {

    ScheduleElement toEntity(ScheduleElementDto dto);

    ScheduleElementDto toDto(ScheduleElement entity);

    List<ScheduleElement> toEntityList(List<ScheduleElementDto> dtoList);

    List<ScheduleElementDto> toDtoList(List<ScheduleElement> entityList);
}
