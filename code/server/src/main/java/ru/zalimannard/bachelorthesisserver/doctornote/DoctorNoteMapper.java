package ru.zalimannard.bachelorthesisserver.doctornote;

import ru.zalimannard.bachelorthesisserver.institution.Institution;

public interface DoctorNoteMapper {
    DoctorNote toEntity(DoctorNoteDto dto);

    DoctorNoteDto toDto(DoctorNote entity);

    int obtainInstitutionId(Institution institution);

    Institution obtainInstitution(int institutionId);
}
