package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

public interface ScheduleStatusMapper {

    ScheduleStatus toEntity(ScheduleStatusDto dto);

    ScheduleStatusDto toDto(ScheduleStatus entity);

}
