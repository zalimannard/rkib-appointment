package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.AccessRight;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccessRightRepository implements BaseRepository<AccessRight> {
    protected final JdbcOperations jdbcOperations;

    public AccessRightRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(AccessRight accessRight) {
        String query = "INSERT INTO access_rights(access_right_name) " +
                "VALUES(?) ";
        Object[] parameters = new Object[]{accessRight.name()};
        jdbcOperations.update(query, parameters);
    }

    @Override
    public AccessRight retrieve(int id) {
        String query = "SELECT access_right_id, access_right_name " +
                "FROM access_rights " +
                "WHERE access_right_id = ? ";
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
    public List<AccessRight> retrieveAll() {
        String query = "SELECT access_right_id, access_right_name " +
                "FROM access_rights ";
        SqlRowSet accessRights = jdbcOperations.queryForRowSet(query);

        List<AccessRight> response = new ArrayList<>();
        while (accessRights.next()) {
            response.add(new AccessRight(
                    accessRights.getInt("access_right_id"),
                    accessRights.getString("access_right_name")
            ));
        }
        return response;
    }

    @Override
    public void update(AccessRight accessRight) {
        String query = "UPDATE access_rights " +
                "SET access_right_name = ? " +
                "WHERE access_right_id = ? ";
        Object[] parameters = new Object[]{accessRight.name(), accessRight.id()};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Право доступа не изменено");
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM access_rights " +
                "WHERE access_right_id = ? ";
        Object[] parameters = new Object[]{id};

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Право доступа не удалено");
        }
    }
}
