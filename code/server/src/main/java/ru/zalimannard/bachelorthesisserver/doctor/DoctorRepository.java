package ru.zalimannard.bachelorthesisserver.doctor;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorRepository {
    protected final JdbcOperations jdbcOperations;

    public DoctorRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(Doctor doctor) {
        String query = """
                INSERT INTO
                    employees(
                        last_name,
                        first_name,
                        middle_name,
                        login,
                        password_hash,
                        password_salt
                    )
                VALUES
                    (?, ?, ?, ?, ?, ?)
                """;
        Object[] parameters = new Object[]{
                doctor.lastName(),
                doctor.firstName(),
                doctor.middleName(),
                doctor.login(),
                doctor.passwordHash(),
                doctor.passwordSalt()
        };

        jdbcOperations.update(query, parameters);
    }

    public Doctor retrieve(int id) {
        String query = """
                SELECT
                    employee_id,
                    last_name,
                    first_name,
                    middle_name,
                    login,
                    password_hash,
                    password_salt
                FROM
                    employees
                WHERE
                    employee_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new Doctor(
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getString("last_name"),
                    sqlRowSet.getString("first_name"),
                    sqlRowSet.getString("middle_name"),
                    sqlRowSet.getString("login"),
                    sqlRowSet.getString("password_hash"),
                    sqlRowSet.getString("password_salt")
            );
        } else {
            throw new NotFoundException("Работник не найден");
        }
    }

    public List<Doctor> retrieveAll() {
        String query = """
                SELECT
                    employee_id,
                    last_name,
                    first_name,
                    middle_name,
                    login,
                    password_hash,
                    password_salt
                FROM
                    employees
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<Doctor> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Doctor(
                    sqlRowSet.getInt("employee_id"),
                    sqlRowSet.getString("last_name"),
                    sqlRowSet.getString("first_name"),
                    sqlRowSet.getString("middle_name"),
                    sqlRowSet.getString("login"),
                    sqlRowSet.getString("password_hash"),
                    sqlRowSet.getString("password_salt")
            ));
        }
        return response;
    }

    public void update(Doctor doctor) {
        String query = """
                UPDATE
                    employees
                SET
                    last_name = ?,
                    first_name = ?,
                    middle_name = ?,
                    login = ?,
                    password_hash = ?,
                    password_salt = ?
                WHERE
                    employee_id = ?
                """;
        Object[] parameters = new Object[]{
                doctor.lastName(),
                doctor.firstName(),
                doctor.middleName(),
                doctor.login(),
                doctor.passwordHash(),
                doctor.passwordSalt(),
                doctor.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Работник не изменён");
        }
    }

    public void delete(int id) {
        String query = """
                DELETE FROM
                    employees
                WHERE
                    employee_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Работник не удалён");
        }
    }
}
