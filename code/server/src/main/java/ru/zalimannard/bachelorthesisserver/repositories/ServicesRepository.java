package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Services;

import java.util.List;

@Repository
public class ServicesRepository implements BaseRepository<Services> {
    protected final JdbcOperations jdbcOperations;

    public ServicesRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Services services) {

    }

    @Override
    public Services retrieve(int id) {
        return null;
    }

    @Override
    public List<Services> retrieveAll() {
        return null;
    }

    @Override
    public void update(Services services) {

    }

    @Override
    public void delete(int id) {

    }
}
