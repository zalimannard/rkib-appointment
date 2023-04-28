package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusResponseDto;

@Component
public class ScheduleStatusMapper {

    public ScheduleStatus toEntity(ScheduleStatusRequestDto scheduleStatusRequestDto) {
        return ScheduleStatus.builder()
                .type(scheduleStatusRequestDto.getType())
                .name(scheduleStatusRequestDto.getName())
                .build();
    }

    public ScheduleStatusResponseDto toDto(ScheduleStatus scheduleStatus) {
        return ScheduleStatusResponseDto.builder()
                .id(scheduleStatus.getId())
                .type(scheduleStatus.getType())
                .name(scheduleStatus.getName())
                .build();
    }

}
