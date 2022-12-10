package ru.zalimannard.bachelorthesisserver.application;

import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.patient.Patient;

public interface ApplicationMapper {
    Application toEntity(ApplicationDto dto);

    ApplicationDto toDto(Application entity);

    int obtainParentApplicationId(Application application);

    Application obtainParentApplication(int applicationId);

    int obtainPatientId(Patient patient);

    Patient obtainPatient(int patientId);

    int obtainDoctorNoteId(DoctorNote doctorNote);

    DoctorNote obtainDoctorNote(int doctorNoteId);

    int obtainStatusId(ApplicationStatus applicationStatus);

    ApplicationStatus obtainStatus(int applicationStatusId);
}
