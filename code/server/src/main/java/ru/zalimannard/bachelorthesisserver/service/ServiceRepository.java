package ru.zalimannard.bachelorthesisserver.service;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.ConflictException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotModifiedException;
import ru.zalimannard.bachelorthesisserver.BaseRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceRepository implements BaseRepository<Service> {
    protected final JdbcOperations jdbcOperations;

    public ServiceRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Service service) {
        String query = """
                INSERT INTO
                    services(
                        service_name
                    )
                VALUES
                    (?)
                """;
        Object[] parameters = new Object[]{
                service.name()
        };

        jdbcOperations.update(query, parameters);
    }

    @Override
    public Service retrieve(int id) {
        String query = """
                SELECT
                    service_id,
                    service_name
                FROM
                    services
                WHERE
                    service_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new Service(
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getString("service_name")
            );
        } else {
            throw new NotFoundException("Услуга не найдена");
        }
    }

    @Override
    public List<Service> retrieveAll() {
        String query = """
                SELECT
                    service_id,
                    service_name
                FROM
                    services
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<Service> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new Service(
                    sqlRowSet.getInt("service_id"),
                    sqlRowSet.getString("service_name")
            ));
        }
        return response;
    }

    @Override
    public void update(Service service) {
        String query = """
                UPDATE
                    services
                SET
                    service_name = ?
                WHERE
                    service_id = ?
                """;
        Object[] parameters = new Object[]{
                service.name(),
                service.id()
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyUpdated = (jdbcOperationsResult == 1);
        if (!isSuccessfullyUpdated) {
            throw new NotModifiedException("Услуга не изменена");
        }
    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM
                    services
                WHERE
                    service_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        int jdbcOperationsResult = jdbcOperations.update(query, parameters);
        boolean isSuccessfullyDeleted = (jdbcOperationsResult == 1);
        if (!isSuccessfullyDeleted) {
            throw new ConflictException("Услуга не удалена");
        }
    }
}
