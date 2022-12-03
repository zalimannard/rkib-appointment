package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.VisitType;
import ru.zalimannard.bachelorthesisserver.exceptions.NotImplementedException;

import java.util.List;

@Repository
public class VisitTypeRepository implements BaseRepository<VisitType> {
    protected final JdbcOperations jdbcOperations;

    public VisitTypeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(VisitType visitType) {
        throw new NotImplementedException("Невозможно добавить тип обращения");
    }

    @Override
    public VisitType retrieve(int id) {
        return null;
    }

    @Override
    public List<VisitType> retrieveAll() {
        return null;
    }

    @Override
    public void update(VisitType visitType) {
        throw new NotImplementedException("Невозможно изменить тип обращения");
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException("Невозможно удалить тип обращения");
    }
}
