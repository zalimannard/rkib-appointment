package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleElementStatusRepository extends JpaRepository<ScheduleElementStatus, String> {

}
