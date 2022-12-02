package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Role;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepository implements BaseRepository<Role> {
    protected final JdbcOperations jdbcOperations;

    public RoleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(Role role) {
        String query = "INSERT INTO roles(role_name) " +
                "VALUES(?) ";
        Object[] parameters = new Object[]{role.name()};
        jdbcOperations.update(query, parameters);
    }

    @Override
    public Role retrieve(int id) {
        String query = "SELECT role_id, role_name " +
                "FROM roles " +
                "WHERE role_id = ? ";
        Object[] parameters = new Object[]{id};
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);

        if (sqlRowSet.next()) {
            return new Role(
                    sqlRowSet.getInt("access_right_id"),
                    sqlRowSet.getString("access_right_name")
            );
        } else {
            throw new NotFoundException("Право доступа не найдено");
        }
    }

    @Override
    public List<Role> retrieveAll() {
        String query = "SELECT role_id, role_name " +
                "FROM roles ";
        SqlRowSet accessRights = jdbcOperations.queryForRowSet(query);

        List<Role> response = new ArrayList<>();
        while (accessRights.next()) {
            response.add(new Role(
                    accessRights.getInt("access_right_id"),
                    accessRights.getString("access_right_name")
            ));
        }
        return response;
    }

    @Override
    public void update(Role role) {
        String query = "UPDATE roles " +
                "SET role_name = ? " +
                "WHERE role_id = ? ";
        Object[] parameters = new Object[]{role.name(), role.id()};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Право доступа не изменено");
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM roles " +
                "WHERE role_id = ? ";
        Object[] parameters = new Object[]{id};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Право доступа не удалено");
        }
    }
}
