package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;
import ru.zalimannard.bachelorthesisserver.BaseRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleElementStatusRepository implements BaseRepository<ScheduleElementStatus> {
    protected final JdbcOperations jdbcOperations;

    public ScheduleElementStatusRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(ScheduleElementStatus scheduleElementStatus) {
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
                scheduleElementStatus.typeId(),
                scheduleElementStatus.name()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public ScheduleElementStatus retrieve(int id) {
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
            return new ScheduleElementStatus(
                    sqlRowSet.getInt("plan_el_status_id"),
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_status_name")
            );
        } else {
            throw new NotFoundException("Статус элемента плана не найден");
        }
    }

    @Override
    public List<ScheduleElementStatus> retrieveAll() {
        String query = """
                SELECT
                    plan_el_status_id,
                    plan_el_type_id,
                    plan_status_name
                FROM
                    plan_el_sttss
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<ScheduleElementStatus> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new ScheduleElementStatus(
                    sqlRowSet.getInt("plan_el_status_id"),
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_status_name")
            ));
        }
        return response;
    }

    @Override
    public void update(ScheduleElementStatus scheduleElementStatus) {
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
                scheduleElementStatus.typeId(),
                scheduleElementStatus.name(),
                scheduleElementStatus.id()
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
