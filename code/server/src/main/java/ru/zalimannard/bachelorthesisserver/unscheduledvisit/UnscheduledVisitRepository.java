package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnscheduledVisitRepository extends CrudRepository<UnscheduledVisit, String> {

}
