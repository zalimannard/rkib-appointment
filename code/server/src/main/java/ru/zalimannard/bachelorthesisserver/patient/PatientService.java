package ru.zalimannard.bachelorthesisserver.patient;

import java.util.List;

public interface PatientService {
    PatientDto get(int id);

    List<PatientDto> list();

    PatientDto create(PatientDto patientDto);

    PatientDto update(PatientDto patientDto);

    PatientDto delete(int id);
}
