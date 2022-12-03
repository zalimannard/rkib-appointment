package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Visit;

import java.util.List;

@Repository
public class VisitRepository implements BaseRepository<Visit> {
    protected final JdbcOperations jdbcOperations;

    public VisitRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Visit visit) {

    }

    @Override
    public Visit retrieve(int id) {
        return null;
    }

    @Override
    public List<Visit> retrieveAll() {
        return null;
    }

    @Override
    public void update(Visit visit) {

    }

    @Override
    public void delete(int id) {

    }
}
