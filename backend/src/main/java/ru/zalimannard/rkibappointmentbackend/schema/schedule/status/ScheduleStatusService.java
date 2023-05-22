package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusResponseDto;

import java.util.List;

@Validated
public interface ScheduleStatusService {

    ScheduleStatusResponseDto create(@NotNull @Valid ScheduleStatusRequestDto procedureDto);

    ScheduleStatus createEntity(@NotNull ScheduleStatus scheduleStatus);


    ScheduleStatusResponseDto read(@NotNull String id);

    ScheduleStatus readEntity(@NotNull String id);

    List<ScheduleStatusResponseDto> readAll();

    List<ScheduleStatus> readAllEntities();


    ScheduleStatusResponseDto update(@NotNull String id, @NotNull @Valid ScheduleStatusRequestDto procedureDto);

    ScheduleStatus updateEntity(@NotNull ScheduleStatus scheduleStatus);


    void delete(@NotNull String id);

}
