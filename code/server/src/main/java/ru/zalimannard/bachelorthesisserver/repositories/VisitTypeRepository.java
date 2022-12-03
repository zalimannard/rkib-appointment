package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.exceptions.NotImplementedException;

import java.util.List;

@Repository
public class VisitTypeRepository implements BaseRepository<Employee> {
    protected final JdbcOperations jdbcOperations;

    public VisitTypeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(Employee employee) {
        throw new NotImplementedException("Невозможно добавить тип обращения");
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
        throw new NotImplementedException("Невозможно изменить тип обращения");
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException("Невозможно удалить тип обращения");
    }
}
