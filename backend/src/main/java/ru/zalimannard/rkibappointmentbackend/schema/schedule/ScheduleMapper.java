package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import org.mapstruct.Mapping;
import ru.zalimannard.rkibappointmentbackend.schema.application.Application;
import ru.zalimannard.rkibappointmentbackend.schema.application.ApplicationDto;

public interface ScheduleMapper {

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "status", ignore = true)
    Application toEntity(ApplicationDto dto);

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "doctorId", ignore = true)
    @Mapping(target = "favorId", ignore = true)
    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "statusId", ignore = true)
    ApplicationDto toDto(Application entity);

}
