package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Role;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepository implements BaseRepository<Role> {
    protected final JdbcOperations jdbcOperations;

    public RoleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Role role) {
        throw new NotImplementedException("Невозможно добавить роль");
    }

    @Override
    public Role retrieve(int id) {
        String query = """
                SELECT
                    role_id,
                    role_name
                FROM
                    roles
                WHERE
                    role_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new Role(
                    sqlRowSet.getInt("role_id"),
                    sqlRowSet.getString("role_name")
            );
        } else {
            throw new NotFoundException("Право доступа не найдено");
        }
    }

    @Override
    public List<Role> retrieveAll() {
        String query = """
                SELECT
                    role_id,
                    role_name
                FROM
                    roles
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<Role> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Role(
                    sqlRowSet.getInt("role_id"),
                    sqlRowSet.getString("role_name")
            ));
        }
        return response;
    }

    @Override
    public void update(Role role) {
        throw new NotImplementedException("Невозможно изменить роль");
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException("Невозможно удалить роль");
    }
}
