package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import java.util.List;

public interface ScheduledVisitService {

    ScheduledVisitDto get(int id);

    List<ScheduledVisitDto> list();

    ScheduledVisitDto create(ScheduledVisitDto doctorNoteDto);

    ScheduledVisitDto update(ScheduledVisitDto doctorNoteDto);

    ScheduledVisitDto delete(int id);
}
