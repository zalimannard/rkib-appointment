package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import jakarta.validation.Valid;

import java.util.List;

public interface ScheduleStatusService {

    ScheduleStatusDto create(@Valid ScheduleStatusDto scheduleStatusDto);

    ScheduleStatusDto read(String id);

    List<ScheduleStatusDto> search(ScheduleStatusDto filterScheduleStatusDto, int pageNo, int pageSize, String[] sortBy);

    ScheduleStatusDto update(String id, @Valid ScheduleStatusDto scheduleStatusDto);

    ScheduleStatusDto delete(String id);

}
