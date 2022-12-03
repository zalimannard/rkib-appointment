package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementType;
import ru.zalimannard.bachelorthesisserver.exceptions.NotImplementedException;

import java.util.List;

@Repository
public class PlanElementTypeRepository implements BaseRepository<PlanElementType> {
    protected final JdbcOperations jdbcOperations;

    public PlanElementTypeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(PlanElementType planElementType) {
        throw new NotImplementedException("Невозможно добавить тип элемента плана");
    }

    @Override
    public PlanElementType retrieve(int id) {
        return null;
    }

    @Override
    public List<PlanElementType> retrieveAll() {
        return null;
    }

    @Override
    public void update(PlanElementType planElementType) {
        throw new NotImplementedException("Невозможно изменить тип элемента плана");
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException("Невозможно удалить тип элемента плана");
    }
}
