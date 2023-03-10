package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleStatusRepository extends JpaRepository<ScheduleStatus, String> {

}
