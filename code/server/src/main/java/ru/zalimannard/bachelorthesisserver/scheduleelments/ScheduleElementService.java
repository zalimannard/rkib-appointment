package ru.zalimannard.bachelorthesisserver.scheduleelments;

import java.util.List;

public interface ScheduleElementService {
    ScheduleElementDto get(int id);

    List<ScheduleElementDto> getAll();

    ScheduleElementDto post(ScheduleElementDto scheduleElementDto);

    ScheduleElementDto put(ScheduleElementDto scheduleElementDto);

    ScheduleElementDto delete(int id);
}
