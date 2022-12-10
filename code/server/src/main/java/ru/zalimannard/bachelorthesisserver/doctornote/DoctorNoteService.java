package ru.zalimannard.bachelorthesisserver.doctornote;

import java.util.List;

public interface DoctorNoteService {
    DoctorNoteDto read(int id);

    List<DoctorNoteDto> list();

    DoctorNoteDto create(DoctorNoteDto doctorNoteDto);

    DoctorNoteDto update(DoctorNoteDto doctorNoteDto);

    DoctorNoteDto delete(int id);
}
