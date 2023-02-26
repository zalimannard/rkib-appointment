package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import java.util.List;

public interface ScheduleElementStatusService {
    ScheduleElementStatusDto get(String id);

    List<ScheduleElementStatusDto> list(ScheduleElementStatusDto exampleScheduleElementStatusDto);

    ScheduleElementStatusDto create(ScheduleElementStatusDto scheduleElementStatusDto);

    ScheduleElementStatusDto update(ScheduleElementStatusDto scheduleElementStatusDto);

    ScheduleElementStatusDto delete(String id);
}
