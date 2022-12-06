package ru.zalimannard.bachelorthesisserver.appointment.status.type;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.exceptions.NotImplementedException;
import ru.zalimannard.bachelorthesisserver.BaseRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentStatusTypeRepository implements BaseRepository<AppointmentStatusType> {
    protected final JdbcOperations jdbcOperations;

    public AppointmentStatusTypeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(AppointmentStatusType appointmentStatusType) {
        throw new NotImplementedException("Невозможно добавить тип обращения");
    }

    @Override
    public AppointmentStatusType retrieve(int id) {
        String query = """
                SELECT
                    visit_type_id,
                    visit_type_name
                FROM
                    visit_types
                WHERE
                    visit_type_id = ?
                """;
        Object[] parameters = new Object[]{
                id
        };

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query, parameters);
        if (sqlRowSet.next()) {
            return new AppointmentStatusType(
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_type_name")
            );
        } else {
            throw new NotFoundException("Тип обращения не найден");
        }
    }

    @Override
    public List<AppointmentStatusType> retrieveAll() {
        String query = """
                SELECT
                    visit_type_id,
                    visit_type_name
                FROM
                    visit_types
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<AppointmentStatusType> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new AppointmentStatusType(
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_type_name")
            ));
        }
        return response;
    }

    @Override
    public void update(AppointmentStatusType appointmentStatusType) {
        throw new NotImplementedException("Невозможно изменить тип обращения");
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException("Невозможно удалить тип обращения");
    }
}
