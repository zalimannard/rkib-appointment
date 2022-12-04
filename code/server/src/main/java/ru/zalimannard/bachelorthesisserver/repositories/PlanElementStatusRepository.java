package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementStatus;
import ru.zalimannard.bachelorthesisserver.entities.VisitStatus;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanElementStatusRepository implements BaseRepository<PlanElementStatus> {
    protected final JdbcOperations jdbcOperations;

    public PlanElementStatusRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(PlanElementStatus planElementStatus) {
        String query = """
                INSERT INTO
                    plan_el_sttss(
                        plan_el_type_id,
                        plan_status_name
                    )
                VALUES
                    (?, ?)
                """;
        Object[] parameters = new Object[]{
                planElementStatus.typeId(),
                planElementStatus.name()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public PlanElementStatus retrieve(int id) {
        String query = """
                SELECT
                    plan_el_status_id,
                    plan_el_type_id,
                    plan_status_name
                FROM
                    plan_el_sttss
                WHERE
                    plan_el_status_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new PlanElementStatus(
                    sqlRowSet.getInt("plan_el_status_id"),
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_status_name")
            );
        } else {
            throw new NotFoundException("Статус элемента плана не найден");
        }
    }

    @Override
    public List<PlanElementStatus> retrieveAll() {
        String query = """
                SELECT
                    plan_el_status_id,
                    plan_el_type_id,
                    plan_status_name
                FROM
                    plan_el_sttss
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<PlanElementStatus> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new PlanElementStatus(
                    sqlRowSet.getInt("plan_el_status_id"),
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_status_name")
            ));
        }
        return response;
    }

    @Override
    public void update(PlanElementStatus planElementStatus) {
        String query = """
                UPDATE
                    plan_el_sttss
                SET
                    plan_el_type_id = ?,
                    plan_status_name = ?
                WHERE
                    plan_el_status_id = ?
                """;
        Object[] parameters = new Object[]{
                planElementStatus.typeId(),
                planElementStatus.name(),
                planElementStatus.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Статус элемента плана не изменён");
        }
    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM
                    plan_el_sttss
                WHERE
                    plan_el_status_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Статус элемента плана не удалён");
        }
    }
}
