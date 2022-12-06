package ru.zalimannard.bachelorthesisserver.application.status.type;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationStatusTypeRepository {
    protected final JdbcOperations jdbcOperations;

    public ApplicationStatusTypeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public ApplicationStatusType retrieve(int id) {
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
            return new ApplicationStatusType(
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_type_name")
            );
        } else {
            throw new NotFoundException("Тип обращения не найден");
        }
    }

    public List<ApplicationStatusType> retrieveAll() {
        String query = """
                SELECT
                    visit_type_id,
                    visit_type_name
                FROM
                    visit_types
                """;

        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(query);
        List<ApplicationStatusType> response = new ArrayList<>();
        while (sqlRowSet.next()) {
            response.add(new ApplicationStatusType(
                    sqlRowSet.getInt("visit_type_id"),
                    sqlRowSet.getString("visit_type_name")
            ));
        }
        return response;
    }
}
