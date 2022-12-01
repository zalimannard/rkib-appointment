package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.EmployeeAccessRight;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeAccessRightRepository implements BaseRepository<EmployeeAccessRight> {
    protected final JdbcOperations jdbcOperations;

    public EmployeeAccessRightRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(EmployeeAccessRight employee) {
        String query = "INSERT INTO employees_access_rights(employee_id, access_right_id) " +
                "VALUES(?, ?) ";
        Object[] parameters = new Object[]{employee.employeeId(), employee.accessRightId()};

        try {
            jdbcOperations.update(query, parameters);
        } catch (Exception e) {
            throw new ConflictException("Связь работника и прав доступа не создана");
        }
    }

    @Override
    public EmployeeAccessRight retrieve(int id) {
        String query = "SELECT employees_access_right_id, employee_id, access_right_id " +
                "FROM employees_access_rights " +
                "WHERE employees_access_right_id = ? ";

        Object[] parameters = new Object[]{id};
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);

        if (sqlRowSet.next()) {
            return new EmployeeAccessRight(
                    sqlRowSet.getInt("employees_access_right_id"),
                    sqlRowSet.getInt("access_right_id"),
                    sqlRowSet.getInt("employee_id")
            );
        } else {
            throw new NotFoundException("Связь работника и прав доступа не найдена");
        }
    }

    @Override
    public List<EmployeeAccessRight> retrieveAll() {
        String query = "SELECT employees_access_right_id, employee_id, access_right_id " +
                "FROM employees_access_rights ";
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);

        List<EmployeeAccessRight> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new EmployeeAccessRight(
                    sqlRowSet.getInt("employees_access_right_id"),
                    sqlRowSet.getInt("access_right_id"),
                    sqlRowSet.getInt("employee_id")
            ));
        }
        return response;
    }

    @Override
    public void update(EmployeeAccessRight employeeAccessRight) {
        String query = "UPDATE employees_access_rights " +
                "SET employee_id = ?, access_right_id = ? " +
                "WHERE employees_access_right_id = ? ";
        Object[] parameters = new Object[]{employeeAccessRight.employeeId(), employeeAccessRight.accessRightId(),
                employeeAccessRight.id()};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Связь работника и прав доступа не изменена");
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM employees_access_rights " +
                "WHERE employees_access_right_id = ? ";
        Object[] parameters = new Object[]{id};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Связь работника и прав доступа не удалена");
        }
    }
}