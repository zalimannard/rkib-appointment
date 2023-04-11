package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleStatusMapper {

    ScheduleStatus toEntity(ScheduleStatusDto dto);

    ScheduleStatusDto toDto(ScheduleStatus entity);

    List<ScheduleStatus> toEntityList(List<ScheduleStatusDto> dto);

    List<ScheduleStatusDto> toDtoList(List<ScheduleStatus> entity);

}
