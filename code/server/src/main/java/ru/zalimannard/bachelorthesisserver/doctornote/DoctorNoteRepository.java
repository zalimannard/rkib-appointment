package ru.zalimannard.bachelorthesisserver.doctornote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorNoteRepository extends JpaRepository<DoctorNote, String> {

}
