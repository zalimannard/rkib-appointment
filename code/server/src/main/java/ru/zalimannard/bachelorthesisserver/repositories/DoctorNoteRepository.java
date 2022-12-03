package ru.zalimannard.bachelorthesisserver.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.zalimannard.bachelorthesisserver.entities.DoctorNote;

import java.util.List;

@Repository
public class DoctorNoteRepository implements BaseRepository<DoctorNote> {
    protected final JdbcOperations jdbcOperations;

    public DoctorNoteRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(DoctorNote doctorNote) {

    }

    @Override
    public DoctorNote retrieve(int id) {
        return null;
    }

    @Override
    public List<DoctorNote> retrieveAll() {
        return null;
    }

    @Override
    public void update(DoctorNote doctorNote) {

    }

    @Override
    public void delete(int id) {

    }
}
