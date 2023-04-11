package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.List;

public interface ScheduleService {

    ScheduleDto create(ScheduleDto scheduleDto);

    Schedule createEntity(@Valid Schedule schedule);


    ScheduleDto read(String id);

    Schedule readEntity(@NotNull String id);


    List<ScheduleDto> search(ScheduleDto filterDto, Date beginTimestamp, Date endTimestamp, String[] sortBy,
                             @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Schedule> searchEntities(Schedule filter, Date beginTimestamp, Date endTimestamp, String[] sortBy,
                                  @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Schedule> searchEntities(Schedule filter, Date beginTimestamp, Date endTimestamp,
                                  @Positive int pageSize, @PositiveOrZero int pageNumber);


    ScheduleDto update(String id, @Valid ScheduleDto scheduleDto);

    Schedule updateEntity(@NotBlank String id, @Valid Schedule schedule);


    void delete(@NotBlank String id);

}
