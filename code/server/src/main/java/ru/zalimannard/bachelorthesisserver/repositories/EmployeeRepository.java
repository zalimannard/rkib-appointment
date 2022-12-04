package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository implements BaseRepository<Employee> {
    protected final JdbcOperations jdbcOperations;

    public EmployeeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Employee employee) {
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
                employee.lastName(),
                employee.firstName(),
                employee.middleName(),
                employee.login(),
                employee.passwordHash(),
                employee.passwordSalt()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public Employee retrieve(int id) {
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
            return new Employee(
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

    @Override
    public List<Employee> retrieveAll() {
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
        List<Employee> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Employee(
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

    @Override
    public void update(Employee employee) {
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
                employee.lastName(),
                employee.firstName(),
                employee.middleName(),
                employee.login(),
                employee.passwordHash(),
                employee.passwordSalt(),
                employee.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Работник не изменён");
        }
    }

    @Override
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
