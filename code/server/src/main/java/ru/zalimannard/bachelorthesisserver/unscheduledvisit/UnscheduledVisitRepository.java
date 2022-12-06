package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UnscheduledVisitRepository {
    protected final JdbcOperations jdbcOperations;

    public UnscheduledVisitRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(UnscheduledVisit unscheduledVisit) {
        String query = """
                INSERT INTO
                    unscheduledVisit(
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
                unscheduledVisit.employeeId(),
                unscheduledVisit.serviceId(),
                unscheduledVisit.visitId(),
                unscheduledVisit.timeStamp(),
                unscheduledVisit.commentary()
        };

        jdbcOperations.update(query, parameters);
    }

    public UnscheduledVisit retrieve(int id) {
        String query = """
                SELECT
                    unscheduled_id,
                    employee_id,
                    service_id,
                    visit_id,
                    time_stamp,
                    commentary
                FROM
                    unscheduledVisit
                WHERE
                    unscheduled_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new UnscheduledVisit(
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

    public List<UnscheduledVisit> retrieveAll() {
        String query = """
                SELECT
                    unscheduled_id,
                    employee_id,
                    service_id,
                    visit_id,
                    time_stamp,
                    commentary
                FROM
                    unscheduledVisit
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<UnscheduledVisit> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new UnscheduledVisit(
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

    public void update(UnscheduledVisit unscheduledVisit) {
        String query = """
                UPDATE
                    unscheduledVisit
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
                unscheduledVisit.employeeId(),
                unscheduledVisit.serviceId(),
                unscheduledVisit.visitId(),
                unscheduledVisit.timeStamp(),
                unscheduledVisit.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Незапланированное посещение не изменено");
        }
    }

    public void delete(int id) {
        String query = """
                DELETE FROM
                    unscheduledVisit
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
