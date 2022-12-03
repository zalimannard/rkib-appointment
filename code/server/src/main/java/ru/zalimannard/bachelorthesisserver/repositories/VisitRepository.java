package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.DoctorNote;
import ru.zalimannard.bachelorthesisserver.entities.Visit;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VisitRepository implements BaseRepository<Visit> {
    protected final JdbcOperations jdbcOperations;

    public VisitRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Visit visit) {
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
                visit.parentVisitId(),
                visit.patientId(),
                visit.serviceId(),
                visit.doctorNoteId(),
                visit.statusId(),
                visit.finalDiagnosis()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public Visit retrieve(int id) {
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
            return new Visit(
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

    @Override
    public List<Visit> retrieveAll() {
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
        List<Visit> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Visit(
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

    @Override
    public void update(Visit visit) {
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
                visit.parentVisitId(),
                visit.patientId(),
                visit.serviceId(),
                visit.doctorNoteId(),
                visit.statusId(),
                visit.finalDiagnosis(),
                visit.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Направление не изменено");
        }
    }

    @Override
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
            throw new ConflictException("Направление не удалено");
        }
    }
}
