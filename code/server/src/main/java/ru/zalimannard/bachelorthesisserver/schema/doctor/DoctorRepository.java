package ru.zalimannard.bachelorthesisserver.schema.doctor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    @Query("""
            SELECT
                d
            FROM
                Doctor d
            WHERE
                (:lastName IS NULL OR lower(d.lastName) LIKE lower(concat('%', :lastName, '%')))
            AND
                (:firstName IS NULL OR lower(d.firstName) LIKE lower(concat('%', :firstName, '%')))
            AND
                (:patronymic IS NULL OR lower(d.patronymic) LIKE lower(concat('%', :patronymic, '%')))
            """)
    List<Doctor> search(@Param("lastName") String lastName,
                        @Param("firstName") String firstName,
                        @Param("patronymic") String patronymic,
                        Pageable pageable);

}
