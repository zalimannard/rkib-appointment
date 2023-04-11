package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type.ScheduleStatusType;

import java.util.List;

@Repository
public interface ScheduleStatusRepository extends JpaRepository<ScheduleStatus, String> {

    @Query("""
            SELECT
                s
            FROM
                ScheduleStatus s
            WHERE
                (:type IS NULL OR s.type = :type)
            AND
                (:name IS NULL OR lower(s.name) LIKE lower(concat('%', :name, '%')))
            """)
    List<ScheduleStatus> search(@Param("type") ScheduleStatusType type,
                                @Param("name") String name,
                                Pageable pageable);

}
