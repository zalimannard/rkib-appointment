package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Unscheduled;

import java.util.List;

@Repository
public class UnscheduledRepository implements BaseRepository<Unscheduled> {
    protected final JdbcOperations jdbcOperations;

    public UnscheduledRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Unscheduled unscheduled) {

    }

    @Override
    public Unscheduled retrieve(int id) {
        return null;
    }

    @Override
    public List<Unscheduled> retrieveAll() {
        return null;
    }

    @Override
    public void update(Unscheduled unscheduled) {

    }

    @Override
    public void delete(int id) {

    }
}
