package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.AccessRight;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccessRightRepository implements BaseRepository<AccessRight> {
    protected final JdbcOperations jdbcOperations;

    public AccessRightRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(AccessRight accessRight) {
    }

    @Override
    public List<AccessRight> read() {
        String query = "SELECT access_right_id, access_right_name " +
                "FROM access_rights";
        List<AccessRight> response = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);

        while (sqlRowSet.next()) {
            response.add(new AccessRight(
                    sqlRowSet.getInt("access_right_id"),
                    sqlRowSet.getString("access_right_name")
            ));
        }
        return response;
    }

    @Override
    public AccessRight read(int id) {
        String query = "SELECT access_right_id, access_right_name " +
                "FROM access_rights " +
                "WHERE access_right_id = ?";
        Object[] parameters = new Object[]{id};
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);

        if (sqlRowSet.next()) {
            return new AccessRight(
                    sqlRowSet.getInt("access_right_id"),
                    sqlRowSet.getString("access_right_name")
            );
        } else {
            throw new NotFoundException("Право доступа не найдено");
        }
    }

    @Override
    public boolean update(int id, AccessRight accessRight) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
