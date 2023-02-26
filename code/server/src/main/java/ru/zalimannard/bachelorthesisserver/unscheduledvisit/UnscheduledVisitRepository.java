package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnscheduledVisitRepository extends JpaRepository<UnscheduledVisit, String> {

}
