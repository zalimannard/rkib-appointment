package ru.zalimannard.bachelorthesisserver.schema.application.status;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.schema.application.status.type.ApplicationStatusType;

import java.util.List;

@Repository
public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, String> {

    @Query("""
            SELECT
                a
            FROM
                ApplicationStatus a
            WHERE
                (:type IS NULL OR a.type = :type)
            AND
                (:name IS NULL OR lower(a.name) LIKE lower(concat('%', :name, '%')))
            """)
    List<ApplicationStatus> search(@Param("type") ApplicationStatusType type, @Param("name") String name, Pageable pageable);

}
