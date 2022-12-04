package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.PlanElement;
import ru.zalimannard.bachelorthesisserver.entities.VisitStatus;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanElementRepository implements BaseRepository<PlanElement> {
    protected final JdbcOperations jdbcOperations;

    public PlanElementRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(PlanElement planElement) {
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
                planElement.employeeId(),
                planElement.serviceId(),
                planElement.statusId(),
                planElement.timeStamp()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public PlanElement retrieve(int id) {
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
            return new PlanElement(
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

    @Override
    public List<PlanElement> retrieveAll() {
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
        List<PlanElement> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new PlanElement(
                    sqlRowSet.getInt("plan_el_id"),
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getInt("plan_el_stts_id"),
                    sqlRowSet.getTimestamp("time_stamp")
            ));
        }
        return response;
    }

    @Override
    public void update(PlanElement planElement) {
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
                planElement.employeeId(),
                planElement.serviceId(),
                planElement.statusId(),
                planElement.timeStamp(),
                planElement.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Элемент плана не изменён");
        }
    }

    @Override
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
