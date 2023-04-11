package ru.zalimannard.rkibappointmentbackend.schema.institution;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String> {

    @Query("""
            SELECT
                i
            FROM
                Institution i
            WHERE
                (:name IS NULL OR lower(i.name) LIKE lower(concat('%', :name, '%')))
            """)
    List<Institution> search(@Param("name") String name,
                             Pageable pageable);

}
