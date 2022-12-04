package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Unscheduled;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UnscheduledRepository implements BaseRepository<Unscheduled> {
    protected final JdbcOperations jdbcOperations;

    public UnscheduledRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Unscheduled unscheduled) {
        String query = """
                INSERT INTO
                    unscheduled(
                        employee_id,
                        service_id,
                        visit_id,
                        time_stamp,
                        commentary
                    )
                VALUES
                    (?, ?, ?, ?, ?)
                """;
        Object[] parameters = new Object[]{
                unscheduled.employeeId(),
                unscheduled.serviceId(),
                unscheduled.visitId(),
                unscheduled.timeStamp(),
                unscheduled.commentary()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public Unscheduled retrieve(int id) {
        String query = """
                SELECT
                    unscheduled_id,
                    employee_id,
                    service_id,
                    visit_id,
                    time_stamp,
                    commentary
                FROM
                    unscheduled
                WHERE
                    unscheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new Unscheduled(
                    sqlRowSet.getInt("unscheduled_id"),
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getInt("visit_id"),
                    sqlRowSet.getTimestamp("time_stamp"),
                    sqlRowSet.getString("commentary")
            );
        } else {
            throw new NotFoundException("Незапланированное посещение не найдено");
        }
    }

    @Override
    public List<Unscheduled> retrieveAll() {
        String query = """
                SELECT
                    unscheduled_id,
                    employee_id,
                    service_id,
                    visit_id,
                    time_stamp,
                    commentary
                FROM
                    unscheduled
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<Unscheduled> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Unscheduled(
                    sqlRowSet.getInt("unscheduled_id"),
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getInt("visit_id"),
                    sqlRowSet.getTimestamp("time_stamp"),
                    sqlRowSet.getString("commentary")
            ));
        }
        return response;
    }

    @Override
    public void update(Unscheduled unscheduled) {
        String query = """
                UPDATE
                    unscheduled
                SET
                    employee_id = ?,
                    service_id = ?,
                    visit_id = ?,
                    time_stamp = ?,
                    commentary = ?
                WHERE
                    unscheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                unscheduled.employeeId(),
                unscheduled.serviceId(),
                unscheduled.visitId(),
                unscheduled.timeStamp(),
                unscheduled.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Незапланированное посещение не изменено");
        }
    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM
                    unscheduled
                WHERE
                    unscheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Незапланированное посещение не удалено");
        }
    }
}
