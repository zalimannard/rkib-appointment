package ru.zalimannard.bachelorthesisserver.scheduleelments.status.type;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotImplementedException;
import ru.zalimannard.bachelorthesisserver.BaseRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleElementTypeRepository implements BaseRepository<ScheduleElementType> {
    protected final JdbcOperations jdbcOperations;

    public ScheduleElementTypeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(ScheduleElementType scheduleElementType) {
        throw new NotImplementedException("Невозможно добавить тип элемента плана");
    }

    @Override
    public ScheduleElementType retrieve(int id) {
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
            return new ScheduleElementType(
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_el_type_name")
            );
        } else {
            throw new NotFoundException("Тип элемента плана не найден");
        }
    }

    @Override
    public List<ScheduleElementType> retrieveAll() {
        String query = """
                SELECT
                    plan_el_type_id,
                    plan_el_type_name
                FROM
                    plan_el_types
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<ScheduleElementType> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new ScheduleElementType(
                    sqlRowSet.getInt("plan_el_type_id"),
                    sqlRowSet.getString("plan_el_type_name")
            ));
        }
        return response;
    }

    @Override
    public void update(ScheduleElementType scheduleElementType) {
        throw new NotImplementedException("Невозможно изменить тип элемента плана");
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException("Невозможно удалить тип элемента плана");
    }
}
