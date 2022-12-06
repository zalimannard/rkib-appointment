package ru.zalimannard.bachelorthesisserver.appointment.status;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentStatusRepository {
    protected final JdbcOperations jdbcOperations;

    public AppointmentStatusRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(AppointmentStatus appointmentStatus) {
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
                appointmentStatus.typeId(),
                appointmentStatus.name()
        };

        jdbcOperations.update(query, parameters);
    }

    public AppointmentStatus retrieve(int id) {
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
            return new AppointmentStatus(
                    sqlRowSet.getInt("visit_status_id"),
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_status_name")
            );
        } else {
            throw new NotFoundException("Статус посещения не найден");
        }
    }

    public List<AppointmentStatus> retrieveAll() {
        String query = """
                SELECT
                    visit_status_id,
                    visit_type_id,
                    visit_status_name
                FROM
                    visit_statuses
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<AppointmentStatus> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new AppointmentStatus(
                    sqlRowSet.getInt("visit_status_id"),
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_status_name")
            ));
        }
        return response;
    }

    public void update(AppointmentStatus appointmentStatus) {
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
                appointmentStatus.typeId(),
                appointmentStatus.name(),
                appointmentStatus.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Статус посещения не изменён");
        }
    }

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
