package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.PlanElement;

import java.util.List;

@Repository
public class PlanElementRepository implements BaseRepository<PlanElement> {
    protected final JdbcOperations jdbcOperations;

    public PlanElementRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(PlanElement planElement) {

    }

    @Override
    public PlanElement retrieve(int id) {
        return null;
    }

    @Override
    public List<PlanElement> retrieveAll() {
        return null;
    }

    @Override
    public void update(PlanElement planElement) {

    }

    @Override
    public void delete(int id) {

    }
}
