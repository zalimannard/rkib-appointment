package ru.zalimannard.bachelorthesisserver.patient;

import java.util.List;

public interface PatientService {
    PatientDto get(int id);

    List<PatientDto> getAll();

    PatientDto post(PatientDto patientDto);

    PatientDto put(PatientDto patientDto);

    PatientDto delete(int id);
}
