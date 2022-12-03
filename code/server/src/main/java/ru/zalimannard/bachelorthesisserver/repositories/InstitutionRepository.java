package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Institution;

import java.util.List;

@Repository
public class InstitutionRepository implements BaseRepository<Institution> {
    protected final JdbcOperations jdbcOperations;

    public InstitutionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Institution institution) {

    }

    @Override
    public Institution retrieve(int id) {
        return null;
    }

    @Override
    public List<Institution> retrieveAll() {
        return null;
    }

    @Override
    public void update(Institution institution) {

    }

    @Override
    public void delete(int id) {

    }
}
