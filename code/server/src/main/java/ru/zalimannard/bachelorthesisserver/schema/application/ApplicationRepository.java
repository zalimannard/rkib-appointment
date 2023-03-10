package ru.zalimannard.bachelorthesisserver.schema.application;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.schema.institution.Institution;
import ru.zalimannard.bachelorthesisserver.schema.patient.Patient;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {

    @Query("""
            SELECT
                a
            FROM
                Application a
            WHERE
                (:doctorNote IS NULL OR lower(a.doctorNote) LIKE lower(concat('%', :doctorNote, '%')))
            AND
                (:finalDiagnosis IS NULL OR lower(a.finalDiagnosis) LIKE lower(concat('%', :finalDiagnosis, '%')))
            AND
                (:commentary IS NULL OR lower(a.commentary) LIKE lower(concat('%', :commentary, '%')))
            AND
                (:patient IS NULL OR a.patient = :patient)
            AND
                (:sendingInstitution IS NULL OR a.sendingInstitution = :sendingInstitution)
            AND
                (:status IS NULL OR a.status = :status)
            """)
    List<Application> search(@Param("patient") Patient patient,
                             @Param("sendingInstitution") Institution sendingInstitution,
                             @Param("status") ApplicationStatus status,
                             @Param("doctorNote") String doctorNote,
                             @Param("finalDiagnosis") String finalDiagnosis,
                             @Param("commentary") String comment,
                             Pageable pageable);

}
