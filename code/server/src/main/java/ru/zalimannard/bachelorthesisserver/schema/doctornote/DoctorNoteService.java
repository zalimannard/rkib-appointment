package ru.zalimannard.bachelorthesisserver.schema.doctornote;

import java.util.List;

public interface DoctorNoteService {

    DoctorNoteDto get(String id);

    List<DoctorNoteDto> list(DoctorNoteDto exampleDoctorNoteDto);

    DoctorNoteDto create(DoctorNoteDto doctorNoteDto);

    DoctorNoteDto update(DoctorNoteDto doctorNoteDto);

    DoctorNoteDto delete(String id);

}
