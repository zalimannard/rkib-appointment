package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.EmployeesRole;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeesRoleRepository implements BaseRepository<EmployeesRole> {
    protected final JdbcOperations jdbcOperations;

    public EmployeesRoleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(EmployeesRole employee) {
        String query = "INSERT INTO employees_roles(employee_id, role_id) " +
                "VALUES(?, ?) ";
        Object[] parameters = new Object[]{employee.employeeId(), employee.roleId()};

        try {
            jdbcOperations.update(query, parameters);
        } catch (Exception e) {
            throw new ConflictException("Связь работника и прав доступа не создана");
        }
    }

    @Override
    public EmployeesRole retrieve(int id) {
        String query = "SELECT employees_role_id, employee_id, role_id " +
                "FROM employees_roles " +
                "WHERE employees_role_id = ? ";

        Object[] parameters = new Object[]{id};
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);

        if (sqlRowSet.next()) {
            return new EmployeesRole(
                    sqlRowSet.getInt("employees_access_right_id"),
                    sqlRowSet.getInt("access_right_id"),
                    sqlRowSet.getInt("employee_id")
            );
        } else {
            throw new NotFoundException("Связь работника и прав доступа не найдена");
        }
    }

    @Override
    public List<EmployeesRole> retrieveAll() {
        String query = "SELECT employees_role_id, employee_id, role_id " +
                "FROM employees_roles ";
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);

        List<EmployeesRole> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new EmployeesRole(
                    sqlRowSet.getInt("employees_access_right_id"),
                    sqlRowSet.getInt("access_right_id"),
                    sqlRowSet.getInt("employee_id")
            ));
        }
        return response;
    }

    @Override
    public void update(EmployeesRole employeesRole) {
        String query = "UPDATE employees_roles " +
                "SET employee_id = ?, role_id = ? " +
                "WHERE employees_role_id = ? ";
        Object[] parameters = new Object[]{employeesRole.employeeId(), employeesRole.roleId(),
                employeesRole.id()};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Связь работника и прав доступа не изменена");
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM employees_roles " +
                "WHERE employees_role_id = ? ";
        Object[] parameters = new Object[]{id};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Связь работника и прав доступа не удалена");
        }
    }
}