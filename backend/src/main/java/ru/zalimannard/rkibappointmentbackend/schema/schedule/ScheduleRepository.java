package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query("""
    SELECT
        s
    FROM
        Schedule s
    ORDER BY
        s.appointmentTime DESC
    """)
    List<Schedule> findAllSortByTime();

}
