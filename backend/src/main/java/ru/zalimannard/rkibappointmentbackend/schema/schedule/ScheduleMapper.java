package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract Schedule toEntity(ScheduleDto dto);

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "doctorId", ignore = true)
    @Mapping(target = "favorId", ignore = true)
    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "statusId", ignore = true)
    public abstract ScheduleDto toDto(Schedule entity);

    public abstract List<Schedule> toEntityList(List<ScheduleDto> dto);

    public abstract List<ScheduleDto> toDtoList(List<Schedule> entity);

}
