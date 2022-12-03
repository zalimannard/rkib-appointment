package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Employee;

import java.util.List;

@Repository
public class VisitRepository implements BaseRepository<Employee> {
    protected final JdbcOperations jdbcOperations;

    public VisitRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Employee employee) {

    }

    @Override
    public Employee retrieve(int id) {
        return null;
    }

    @Override
    public List<Employee> retrieveAll() {
        return null;
    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(int id) {

    }
}
