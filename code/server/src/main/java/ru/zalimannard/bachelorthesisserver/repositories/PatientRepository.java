package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Patient;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository implements BaseRepository<Patient> {
    protected final JdbcOperations jdbcOperations;

    public PatientRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Patient patient) {
        String query = """
                INSERT INTO
                    patients(
                    last_name,
                    first_name,
                    middle_name,
                    phone_number,
                    birth_date,
                    address,
                    job)
                VALUES
                    (?, ?, ?, ?, ?, ?, ?)
                """;
        Object[] parameters = new Object[]{
                patient.lastName(),
                patient.firstName(),
                patient.middleName(),
                patient.phoneNumber(),
                patient.birthDate(),
                patient.birthDate(),
                patient.job()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public Patient retrieve(int id) {
        String query = """
                SELECT
                    patient_id,
                    last_name,
                    first_name,
                    middle_name,
                    phone_number,
                    birth_date,
                    address,
                    job
                FROM
                    patients
                WHERE
                    patient_id = ?
                """;

        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new Patient(
                    sqlRowSet.getInt("patient_id"),
                    sqlRowSet.getString("last_name"),
                    sqlRowSet.getString("first_name"),
                    sqlRowSet.getString("middle_name"),
                    sqlRowSet.getString("phone_number"),
                    sqlRowSet.getDate("birth_date"),
                    sqlRowSet.getString("address"),
                    sqlRowSet.getString("job")
            );
        } else {
            throw new NotFoundException("Пациент не найден");
        }
    }

    @Override
    public List<Patient> retrieveAll() {
        String query = """
                SELECT
                    patient_id,
                    last_name,
                    first_name,
                    middle_name,
                    phone_number,
                    birth_date,
                    address,
                    job
                FROM
                    patients
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<Patient> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Patient(
                    sqlRowSet.getInt("patient_id"),
                    sqlRowSet.getString("last_name"),
                    sqlRowSet.getString("first_name"),
                    sqlRowSet.getString("middle_name"),
                    sqlRowSet.getString("phone_number"),
                    sqlRowSet.getDate("birth_date"),
                    sqlRowSet.getString("address"),
                    sqlRowSet.getString("job")
            ));
        }
        return response;
    }

    @Override
    public void update(Patient patient) {
        String query = """
                UPDATE
                    patients
                SET
                    last_name = ?,
                    first_name = ?,
                    middle_name = ?,
                    phone_number = ?,
                    birth_date = ?,
                    address = ?,
                    job = ?
                WHERE
                    patient_id = ?
                """;
        Object[] parameters = new Object[]{
                patient.lastName(),
                patient.firstName(),
                patient.middleName(),
                patient.phoneNumber(),
                patient.birthDate(),
                patient.birthDate(),
                patient.job(),
                patient.id()};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Пациент не изменён");
        }
    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM
                    patients
                WHERE
                    patient_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Пациент не удалён");
        }
    }
}
