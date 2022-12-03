package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.DoctorNote;
import ru.zalimannard.bachelorthesisserver.entities.VisitStatus;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VisitStatusRepository implements BaseRepository<VisitStatus> {
    protected final JdbcOperations jdbcOperations;

    public VisitStatusRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(VisitStatus visitStatus) {
        String query = """
                INSERT INTO
                    visit_statuses(
                        visit_type_id,
                        visit_status_name
                    )
                VALUES
                    (?, ?)
                """;
        Object[] parameters = new Object[]{
                visitStatus.typeId(),
                visitStatus.name()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public VisitStatus retrieve(int id) {
        String query = """
                SELECT
                    visit_status_id,
                    visit_type_id,
                    visit_status_name
                FROM
                    visit_statuses
                WHERE
                    visit_status_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new VisitStatus(
                    sqlRowSet.getInt("visit_status_id"),
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_status_name")
            );
        } else {
            throw new NotFoundException("Статус посещения не найден");
        }
    }

    @Override
    public List<VisitStatus> retrieveAll() {
        String query = """
                SELECT
                    visit_status_id,
                    visit_type_id,
                    visit_status_name
                FROM
                    visit_statuses
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<VisitStatus> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new VisitStatus(
                    sqlRowSet.getInt("visit_status_id"),
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_status_name")
            ));
        }
        return response;
    }

    @Override
    public void update(VisitStatus visitStatus) {
        String query = """
                UPDATE
                    visit_statuses
                SET
                    visit_type_id = ?,
                    visit_status_name = ?
                WHERE
                    visit_status_id = ?
                """;
        Object[] parameters = new Object[]{
                visitStatus.typeId(),
                visitStatus.name(),
                visitStatus.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Статус посещения не изменён");
        }
    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM
                    visit_statuses
                WHERE
                    visit_status_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Статус посещения не удалён");
        }
    }
}
