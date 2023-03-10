package ru.zalimannard.bachelorthesisserver.schema.patient;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    @Query("""
            SELECT
                p
            FROM
                Patient p
            WHERE
                (:lastName IS NULL OR lower(p.lastName) LIKE lower(concat('%', :lastName, '%')))
            AND
                (:firstName IS NULL OR lower(p.firstName) LIKE lower(concat('%', :firstName, '%')))
            AND
                (:patronymic IS NULL OR lower(p.patronymic) LIKE lower(concat('%', :patronymic, '%')))
            AND
                (:phoneNumber IS NULL OR lower(p.phoneNumber) LIKE lower(concat('%', :phoneNumber, '%')))
            AND
                (:address IS NULL OR lower(p.address) LIKE lower(concat('%', :address, '%')))
            AND
                (:occupation IS NULL OR lower(p.occupation) LIKE lower(concat('%', :occupation, '%')))
            AND
                (:beginBirthdate IS NULL OR p.birthdate >= :beginBirthdate)
            AND
                (:endBirthdate IS NULL OR p.birthdate <= :endBirthdate)
            """)
    List<Patient> search(@Param("beginBirthdate") Date beginBirthdate,
                         @Param("endBirthdate") Date endBirthdate,
                         @Param("lastName") String lastName,
                         @Param("firstName") String firstName,
                         @Param("patronymic") String patronymic,
                         @Param("phoneNumber") String phoneNumber,
                         @Param("address") String address,
                         @Param("occupation") String occupation,
                         Pageable pageable);

}
