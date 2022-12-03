package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.DoctorNote;
import ru.zalimannard.bachelorthesisserver.entities.Institution;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorNoteRepository implements BaseRepository<DoctorNote> {
    protected final JdbcOperations jdbcOperations;

    public DoctorNoteRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(DoctorNote doctorNote) {
        String query = """
                INSERT INTO
                    doctor_notes(
                        institution_id,
                        preliminary_diagnosis
                    )
                VALUES
                    (?, ?)
                """;
        Object[] parameters = new Object[]{
                doctorNote.institutionId(),
                doctorNote.preliminaryDiagnosis()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public DoctorNote retrieve(int id) {
        String query = """
                SELECT
                    doctor_note_id,
                    institution_id,
                    preliminary_diagnosis
                FROM
                    doctor_notes
                WHERE
                    doctor_note_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new DoctorNote(
                    sqlRowSet.getInt("doctor_note_id"),
                    sqlRowSet.getInt("institution_id"),
                    sqlRowSet.getString("preliminary_diagnosis")
            );
        } else {
            throw new NotFoundException("Направление не найдено");
        }
    }

    @Override
    public List<DoctorNote> retrieveAll() {
        String query = """
                SELECT
                    doctor_note_id,
                    institution_id,
                    preliminary_diagnosis
                FROM
                    doctor_notes
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<DoctorNote> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new DoctorNote(
                    sqlRowSet.getInt("doctor_note_id"),
                    sqlRowSet.getInt("institution_id"),
                    sqlRowSet.getString("preliminary_diagnosis")
            ));
        }
        return response;
    }

    @Override
    public void update(DoctorNote doctorNote) {
        String query = """
                UPDATE
                    doctor_notes
                SET
                    institution_id = ?,
                    preliminary_diagnosis = ?
                WHERE
                    doctor_note_id = ?
                """;
        Object[] parameters = new Object[]{
                doctorNote.institutionId(),
                doctorNote.preliminaryDiagnosis(),
                doctorNote.id()
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
                    doctor_notes
                WHERE
                    doctor_note_id = ?
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
