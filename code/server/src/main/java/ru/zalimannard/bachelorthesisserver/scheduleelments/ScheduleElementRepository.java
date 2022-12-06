package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleElementRepository {
    protected final JdbcOperations jdbcOperations;

    public ScheduleElementRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(ScheduleElement scheduleElement) {
        String query = """
                INSERT INTO
                    plan_els(
                        employee_id,
                        service_id,
                        plan_el_stts_id,
                        time_stamp
                    )
                VALUES
                    (?, ?, ?, ?)
                """;
        Object[] parameters = new Object[]{
                scheduleElement.employeeId(),
                scheduleElement.serviceId(),
                scheduleElement.statusId(),
                scheduleElement.timeStamp()
        };

        jdbcOperations.update(query, parameters);
    }

    public ScheduleElement retrieve(int id) {
        String query = """
                SELECT
                    plan_el_id,
                    employee_id,
                    service_id,
                    plan_el_stts_id,
                    time_stamp
                FROM
                    plan_els
                WHERE
                    plan_el_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new ScheduleElement(
                    sqlRowSet.getInt("plan_el_id"),
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getInt("plan_el_stts_id"),
                    sqlRowSet.getTimestamp("time_stamp")
            );
        } else {
            throw new NotFoundException("Элемент плана не найден");
        }
    }

    public List<ScheduleElement> retrieveAll() {
        String query = """
                SELECT
                    plan_el_id,
                    employee_id,
                    service_id,
                    plan_el_stts_id,
                    time_stamp
                FROM
                    plan_els
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<ScheduleElement> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new ScheduleElement(
                    sqlRowSet.getInt("plan_el_id"),
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getInt("plan_el_stts_id"),
                    sqlRowSet.getTimestamp("time_stamp")
            ));
        }
        return response;
    }

    public void update(ScheduleElement scheduleElement) {
        String query = """
                UPDATE
                    plan_els
                SET
                    employee_id = ?,
                    service_id = ?,
                    plan_el_stts_id = ?,
                    time_stamp = ?
                WHERE
                    plan_el_id = ?
                """;
        Object[] parameters = new Object[]{
                scheduleElement.employeeId(),
                scheduleElement.serviceId(),
                scheduleElement.statusId(),
                scheduleElement.timeStamp(),
                scheduleElement.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Элемент плана не изменён");
        }
    }

    public void delete(int id) {
        String query = """
                DELETE FROM
                    plan_els
                WHERE
                    plan_el_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Элемент плана не удалён");
        }
    }
}
