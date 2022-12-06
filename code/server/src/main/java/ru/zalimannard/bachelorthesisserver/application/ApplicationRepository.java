package ru.zalimannard.bachelorthesisserver.application;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationRepository {
    protected final JdbcOperations jdbcOperations;

    public ApplicationRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(Application application) {
        String query = """
                INSERT INTO
                    visits(
                        parent_visit_id,
                        patient_id,
                        service_id,
                        doctor_note_id,
                        visit_status_id,
                        final_diagnosis
                    )
                VALUES
                    (?, ?, ?, ?, ?, ?)
                """;
        Object[] parameters = new Object[]{
                application.parentVisitId(),
                application.patientId(),
                application.serviceId(),
                application.doctorNoteId(),
                application.statusId(),
                application.finalDiagnosis()
        };

        jdbcOperations.update(query, parameters);
    }

    public Application retrieve(int id) {
        String query = """
                SELECT
                    visit_id,
                    parent_visit_id,
                    patient_id,
                    service_id,
                    doctor_note_id,
                    visit_status_id,
                    final_diagnosis
                FROM
                    visits
                WHERE
                    visit_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new Application(
                    sqlRowSet.getInt("visit_id"),
                    sqlRowSet.getInt("parent_visit_id"),
                    sqlRowSet.getInt("patient_id"),
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getInt("doctor_note_id"),
                    sqlRowSet.getInt("visit_status_id"),
                    sqlRowSet.getString("final_diagnosis")
            );
        } else {
            throw new NotFoundException("Обращение не найдено");
        }
    }

    public List<Application> retrieveAll() {
        String query = """
                SELECT
                    visit_id,
                    parent_visit_id,
                    patient_id,
                    service_id,
                    doctor_note_id,
                    visit_status_id,
                    final_diagnosis
                FROM
                    visits
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<Application> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Application(
                    sqlRowSet.getInt("visit_id"),
                    sqlRowSet.getInt("parent_visit_id"),
                    sqlRowSet.getInt("patient_id"),
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getInt("doctor_note_id"),
                    sqlRowSet.getInt("visit_status_id"),
                    sqlRowSet.getString("final_diagnosis")
            ));
        }
        return response;
    }

    public void update(Application application) {
        String query = """
                UPDATE
                    visits
                SET
                    parent_visit_id = ?,
                    patient_id = ?,
                    service_id = ?,
                    doctor_note_id = ?,
                    visit_status_id = ?,
                    final_diagnosis = ?
                WHERE
                    visit_id = ?
                """;
        Object[] parameters = new Object[]{
                application.parentVisitId(),
                application.patientId(),
                application.serviceId(),
                application.doctorNoteId(),
                application.statusId(),
                application.finalDiagnosis(),
                application.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Обращение не изменено");
        }
    }

    public void delete(int id) {
        String query = """
                DELETE FROM
                    visits
                WHERE
                    visit_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Обращение не удалено");
        }
    }
}
