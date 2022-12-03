package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Scheduled;

import java.util.List;

@Repository
public class ScheduledRepository implements BaseRepository<Scheduled> {
    protected final JdbcOperations jdbcOperations;

    public ScheduledRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Scheduled scheduled) {

    }

    @Override
    public Scheduled retrieve(int id) {
        return null;
    }

    @Override
    public List<Scheduled> retrieveAll() {
        return null;
    }

    @Override
    public void update(Scheduled scheduled) {

    }

    @Override
    public void delete(int id) {

    }
}
