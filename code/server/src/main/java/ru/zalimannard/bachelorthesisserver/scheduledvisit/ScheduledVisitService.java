package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import java.util.List;

public interface ScheduledVisitService {

    ScheduledVisitDto get(String id);

    List<ScheduledVisitDto> list(ScheduledVisitDto exampleScheduledVisitDto);

    ScheduledVisitDto create(ScheduledVisitDto doctorNoteDto);

    ScheduledVisitDto update(ScheduledVisitDto doctorNoteDto);

    ScheduledVisitDto delete(String id);
}
