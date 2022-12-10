package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleElementRepository extends CrudRepository<ScheduleElement, Integer> {

}