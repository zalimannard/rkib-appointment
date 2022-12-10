package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import java.util.List;

public interface ScheduleElementStatusService {
    ScheduleElementStatusDto get(int id);

    List<ScheduleElementStatusDto> getAll();

    ScheduleElementStatusDto post(ScheduleElementStatusDto scheduleElementStatusDto);

    ScheduleElementStatusDto put(ScheduleElementStatusDto scheduleElementStatusDto);

    ScheduleElementStatusDto delete(int id);
}
