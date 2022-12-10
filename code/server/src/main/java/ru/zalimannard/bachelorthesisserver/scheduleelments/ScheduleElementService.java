package ru.zalimannard.bachelorthesisserver.scheduleelments;

import java.util.List;

public interface ScheduleElementService {
    ScheduleElementDto create(int id);

    List<ScheduleElementDto> list();

    ScheduleElementDto create(ScheduleElementDto scheduleElementDto);

    ScheduleElementDto update(ScheduleElementDto scheduleElementDto);

    ScheduleElementDto delete(int id);
}
