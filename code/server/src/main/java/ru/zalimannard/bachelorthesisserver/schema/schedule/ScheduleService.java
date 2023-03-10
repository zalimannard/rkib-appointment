package ru.zalimannard.bachelorthesisserver.schema.schedule;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

public interface ScheduleService {

    ScheduleDto create(@Valid ScheduleDto scheduleDto);

    ScheduleDto read(String id);

    List<ScheduleDto> search(ScheduleDto filterScheduleDto, Date beginTimestamp, Date endTimestamp, int pageNo, int pageSize, String[] sortBy);

    ScheduleDto update(String id, @Valid ScheduleDto scheduleDto);

    ScheduleDto delete(String id);

}
