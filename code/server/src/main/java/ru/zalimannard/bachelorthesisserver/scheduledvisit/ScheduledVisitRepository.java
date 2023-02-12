package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledVisitRepository extends CrudRepository<ScheduledVisit, String> {

}
