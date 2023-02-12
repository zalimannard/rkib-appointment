package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import java.util.List;

public interface UnscheduledVisitService {
    UnscheduledVisitDto get(String id);

    List<UnscheduledVisitDto> list();

    UnscheduledVisitDto create(UnscheduledVisitDto unscheduledVisitDto);

    UnscheduledVisitDto update(UnscheduledVisitDto unscheduledVisitDto);

    UnscheduledVisitDto delete(String id);
}
