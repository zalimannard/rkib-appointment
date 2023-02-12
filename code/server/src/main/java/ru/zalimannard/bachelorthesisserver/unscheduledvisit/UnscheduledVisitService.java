package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import java.util.List;

public interface UnscheduledVisitService {
    UnscheduledVisitDto get(int id);

    List<UnscheduledVisitDto> list();

    UnscheduledVisitDto create(UnscheduledVisitDto unscheduledVisitDto);

    UnscheduledVisitDto update(UnscheduledVisitDto unscheduledVisitDto);

    UnscheduledVisitDto delete(int id);
}
