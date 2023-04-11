package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public interface ScheduleStatusService {

    ScheduleStatusDto create(ScheduleStatusDto scheduleStatusDto);

    ScheduleStatus createEntity(@Valid ScheduleStatus scheduleStatus);


    ScheduleStatusDto read(String id);

    ScheduleStatus readEntity(@NotNull String id);


    List<ScheduleStatusDto> search(ScheduleStatusDto filterDto, String[] sortBy,
                                   @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<ScheduleStatus> searchEntities(ScheduleStatus filter, String[] sortBy,
                                        @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<ScheduleStatus> searchEntities(ScheduleStatus filter,
                                        @Positive int pageSize, @PositiveOrZero int pageNumber);


    ScheduleStatusDto update(String id, @Valid ScheduleStatusDto scheduleStatusDto);

    ScheduleStatus updateEntity(@NotBlank String id, @Valid ScheduleStatus scheduleStatus);


    void delete(@NotBlank String id);

}
