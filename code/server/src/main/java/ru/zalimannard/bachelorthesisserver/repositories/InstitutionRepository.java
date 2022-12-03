package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.entities.Institution;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstitutionRepository implements BaseRepository<Institution> {
    protected final JdbcOperations jdbcOperations;

    public InstitutionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Institution institution) {
        String query = """
                INSERT INTO
                    institutions(institution_name)
                VALUES
                    (?)
                """;
        Object[] parameters = new Object[]{institution.name()};
        jdbcOperations.update(query, parameters);
    }

    @Override
    public Institution retrieve(int id) {
        String query = """
                SELECT
                    institution_id,
                    institution_name
                FROM
                    institutions
                WHERE
                    institution_id = ?
                """;

        Object[] parameters = new Object[]{id};
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);

        if (sqlRowSet.next()) {
            return new Institution(
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getString("last_name")
            );
        } else {
            throw new NotFoundException("Учреждение не найдено");
        }
    }

    @Override
    public List<Institution> retrieveAll() {
        String query = """
                SELECT
                    institution_id,
                    institution_name
                FROM
                    institutions
                """;
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);

        List<Institution> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Institution(
                    sqlRowSet.getInt("institution_id"),
                    sqlRowSet.getString("institution_name")
            ));
        }
        return response;
    }

    @Override
    public void update(Institution institution) {
        String query = """
                UPDATE
                    institutions
                SET
                    institution_name = ?
                WHERE
                    institution_id = ?
                """;
        Object[] parameters = new Object[]{institution.name(), institution.id()};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Учреждение не изменено");
        }
    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM
                    institutions
                WHERE
                    institution_id = ?
                """;
        Object[] parameters = new Object[]{id};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Учреждение не удалено");
        }
    }
}
