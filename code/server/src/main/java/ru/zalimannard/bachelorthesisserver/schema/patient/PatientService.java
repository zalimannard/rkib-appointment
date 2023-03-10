package ru.zalimannard.bachelorthesisserver.schema.patient;

import java.util.List;

public interface PatientService {

    PatientDto get(String id);

    List<PatientDto> list(PatientDto examplePatientDto);

    PatientDto create(PatientDto patientDto);

    PatientDto update(PatientDto patientDto);

    PatientDto delete(String id);

}
