package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleElementStatusRepository extends CrudRepository<ScheduleElementStatus, Integer> {

}
