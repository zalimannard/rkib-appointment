package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zalimannard.rkibappointmentbackend.schema.application.Application;
import ru.zalimannard.rkibappointmentbackend.schema.favor.Favor;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatus;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query("""
            SELECT
                s
            FROM
                Schedule s
            WHERE
                (:doctor IS NULL OR s.doctor = :doctor)
            AND
                (:favor IS NULL OR s.favor = :favor)
            AND
                (:application IS NULL OR s.application = :application)
            AND
                (:status IS NULL OR s.status = :status)
            AND
                (:commentary IS NULL OR lower(s.commentary) LIKE lower(concat('%', :commentary, '%')))
            AND
                (:beginTimestamp IS NULL OR s.appointmentTimestamp >= :beginTimestamp)
            AND
                (:endTimestamp IS NULL OR s.appointmentTimestamp <= :endTimestamp)
            """)
    List<Schedule> search(Person doctor,
                          Favor favor,
                          Application application,
                          ScheduleStatus status,
                          String commentary,
                          Date beginTimestamp,
                          Date endTimestamp,
                          Pageable pageable);
}
