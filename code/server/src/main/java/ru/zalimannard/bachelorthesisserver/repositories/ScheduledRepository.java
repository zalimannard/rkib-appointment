package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Scheduled;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduledRepository implements BaseRepository<Scheduled> {
    protected final JdbcOperations jdbcOperations;

    public ScheduledRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Scheduled scheduled) {
        String query = """
                INSERT INTO
                    scheduled(
                        plan_el_id,
                        visit_id
                    )
                VALUES
                    (?, ?)
                """;
        Object[] parameters = new Object[]{
                scheduled.planElementId(),
                scheduled.visitId()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public Scheduled retrieve(int id) {
        String query = """
                SELECT
                    scheduled_id,
                    plan_el_id,
                    visit_id
                FROM
                    scheduled
                WHERE
                    scheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new Scheduled(
                    sqlRowSet.getInt("scheduled_id"),
                    sqlRowSet.getInt("plan_el_id"),
                    sqlRowSet.getInt("visit_id")
            );
        } else {
            throw new NotFoundException("Запланированное обращение не найдено");
        }
    }

    @Override
    public List<Scheduled> retrieveAll() {
        String query = """
                SELECT
                    scheduled_id,
                    plan_el_id,
                    visit_id
                FROM
                    scheduled
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<Scheduled> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Scheduled(
                    sqlRowSet.getInt("scheduled_id"),
                    sqlRowSet.getInt("plan_el_id"),
                    sqlRowSet.getInt("visit_id")
            ));
        }
        return response;
    }

    @Override
    public void update(Scheduled scheduled) {
        String query = """
                UPDATE
                    scheduled
                SET
                    plan_el_id = ?,
                    visit_id = ?
                WHERE
                    scheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                scheduled.planElementId(),
                scheduled.visitId(),
                scheduled.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Запланированное обращение не изменено");
        }
    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM
                    scheduled
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
