package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduledVisitRepository {
    protected final JdbcOperations jdbcOperations;

    public ScheduledVisitRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(ScheduledVisit scheduledVisit) {
        String query = """
                INSERT INTO
                    scheduledVisit(
                        plan_el_id,
                        visit_id
                    )
                VALUES
                    (?, ?)
                """;
        Object[] parameters = new Object[]{
                scheduledVisit.planElementId(),
                scheduledVisit.visitId()
        };

        jdbcOperations.update(query, parameters);
    }

    public ScheduledVisit retrieve(int id) {
        String query = """
                SELECT
                    scheduled_id,
                    plan_el_id,
                    visit_id
                FROM
                    scheduledVisit
                WHERE
                    scheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new ScheduledVisit(
                    sqlRowSet.getInt("scheduled_id"),
                    sqlRowSet.getInt("plan_el_id"),
                    sqlRowSet.getInt("visit_id")
            );
        } else {
            throw new NotFoundException("Запланированное обращение не найдено");
        }
    }

    public List<ScheduledVisit> retrieveAll() {
        String query = """
                SELECT
                    scheduled_id,
                    plan_el_id,
                    visit_id
                FROM
                    scheduledVisit
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<ScheduledVisit> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new ScheduledVisit(
                    sqlRowSet.getInt("scheduled_id"),
                    sqlRowSet.getInt("plan_el_id"),
                    sqlRowSet.getInt("visit_id")
            ));
        }
        return response;
    }

    public void update(ScheduledVisit scheduledVisit) {
        String query = """
                UPDATE
                    scheduledVisit
                SET
                    plan_el_id = ?,
                    visit_id = ?
                WHERE
                    scheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                scheduledVisit.planElementId(),
                scheduledVisit.visitId(),
                scheduledVisit.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Запланированное обращение не изменено");
        }
    }

    public void delete(int id) {
        String query = """
                DELETE FROM
                    scheduledVisit
                WHERE
                    scheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Запланированное обращение не удалено");
        }
    }
}
