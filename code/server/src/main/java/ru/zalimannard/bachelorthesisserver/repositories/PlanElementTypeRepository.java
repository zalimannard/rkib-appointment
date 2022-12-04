package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementType;
import ru.zalimannard.bachelorthesisserver.entities.VisitType;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotImplementedException;

import java.util.ArrayList;
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
        String query = """
                SELECT
                    plan_el_type_id,
                    plan_el_type_name
                FROM
                    plan_el_types
                WHERE
                    plan_el_type_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new PlanElementType(
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_el_type_name")
            );
        } else {
            throw new NotFoundException("Тип элемента плана не найден");
        }
    }

    @Override
    public List<PlanElementType> retrieveAll() {
        String query = """
                SELECT
                    plan_el_type_id,
                    plan_el_type_name
                FROM
                    plan_el_types
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<PlanElementType> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new PlanElementType(
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_el_type_name")
            ));
        }
        return response;
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
