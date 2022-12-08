package ru.zalimannard.bachelorthesisserver.doctornote;

import java.util.List;

public interface DoctorNoteService {
    DoctorNoteDto get(int id);

    List<DoctorNoteDto> getAll();

    DoctorNoteDto post(DoctorNoteDto doctorNoteDto);

    DoctorNoteDto put(DoctorNoteDto doctorNoteDto);

    DoctorNoteDto delete(int id);
}
