package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import java.util.List;

public interface ScheduleStatusService {

    ScheduleStatusDto get(String id);

    List<ScheduleStatusDto> list(ScheduleStatusDto exampleScheduleStatusDto);

    ScheduleStatusDto create(ScheduleStatusDto scheduleStatusDto);

    ScheduleStatusDto update(ScheduleStatusDto scheduleStatusDto);

    ScheduleStatusDto delete(String id);

}
