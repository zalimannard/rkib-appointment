package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementStatus;

import java.util.List;

@Repository
public class PlanElementStatusRepository implements BaseRepository<PlanElementStatus> {
    protected final JdbcOperations jdbcOperations;

    public PlanElementStatusRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(PlanElementStatus planElementStatus) {

    }

    @Override
    public PlanElementStatus retrieve(int id) {
        return null;
    }

    @Override
    public List<PlanElementStatus> retrieveAll() {
        return null;
    }

    @Override
    public void update(PlanElementStatus planElementStatus) {

    }

    @Override
    public void delete(int id) {

    }
}
