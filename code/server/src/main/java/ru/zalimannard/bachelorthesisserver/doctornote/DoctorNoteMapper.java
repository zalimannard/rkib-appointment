package ru.zalimannard.bachelorthesisserver.doctornote;

public interface DoctorNoteMapper {
    DoctorNote toEntity(DoctorNoteDto dto);

    DoctorNoteDto toDto(DoctorNote entity);
}
