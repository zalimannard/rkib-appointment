package ru.zalimannard.bachelorthesisserver.doctornote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorNoteRepository extends CrudRepository<DoctorNote, Integer> {

}
