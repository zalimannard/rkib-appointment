package ru.zalimannard.bachelorthesisserver.schema.schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleDto get(String id);

    List<ScheduleDto> list(ScheduleDto exampleScheduleDto);

    ScheduleDto create(ScheduleDto scheduleDto);

    ScheduleDto update(ScheduleDto scheduleDto);

    ScheduleDto delete(String id);

}
