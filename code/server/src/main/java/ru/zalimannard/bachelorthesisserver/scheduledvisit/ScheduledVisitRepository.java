package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledVisitRepository extends JpaRepository<ScheduledVisit, String> {

}
