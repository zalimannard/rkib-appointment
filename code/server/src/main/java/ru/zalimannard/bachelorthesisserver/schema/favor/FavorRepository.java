package ru.zalimannard.bachelorthesisserver.schema.favor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorRepository extends JpaRepository<Favor, String> {

    @Query("""
            SELECT
                f
            FROM
                Favor f
            WHERE
                (:name IS NULL OR lower(f.name) LIKE lower(concat('%', :name, '%')))
            """)
    List<Favor> search(@Param("name") String name, Pageable pageable);

}
