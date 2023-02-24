package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleElementRepository extends JpaRepository<ScheduleElement, String> {

}