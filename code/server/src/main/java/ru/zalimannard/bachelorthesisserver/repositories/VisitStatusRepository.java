package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.VisitStatus;

import java.util.List;

@Repository
public class VisitStatusRepository implements BaseRepository<VisitStatus> {
    protected final JdbcOperations jdbcOperations;

    public VisitStatusRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(VisitStatus visitStatus) {

    }

    @Override
    public VisitStatus retrieve(int id) {
        return null;
    }

    @Override
    public List<VisitStatus> retrieveAll() {
        return null;
    }

    @Override
    public void update(VisitStatus visitStatus) {

    }

    @Override
    public void delete(int id) {

    }
}
