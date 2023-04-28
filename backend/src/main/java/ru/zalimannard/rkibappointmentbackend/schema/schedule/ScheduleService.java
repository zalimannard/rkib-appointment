package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleResponseDto;

@Validated
public interface ScheduleService {

    ScheduleResponseDto create(@NotNull @Valid ScheduleRequestDto scheduleDto);

    Schedule createEntity(@NotNull Schedule schedule);


    ScheduleResponseDto read(@NotNull String id);

    Schedule readEntity(@NotNull String id);


    ScheduleResponseDto update(@NotNull String id, @NotNull @Valid ScheduleRequestDto scheduleDto);

    Schedule updateEntity(@NotNull Schedule schedule);


    void delete(@NotNull String id);

}
