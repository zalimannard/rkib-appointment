package ru.zalimannard.bachelorthesisserver.application;

import org.springframework.stereotype.Component;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatusRepository;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNoteRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.patient.Patient;
import ru.zalimannard.bachelorthesisserver.patient.PatientRepository;

import java.util.Optional;

@Component
public class ApplicationMapperImpl implements ApplicationMapper {
    private final ApplicationRepository applicationRepository;
    private final PatientRepository patientRepository;
    private final DoctorNoteRepository doctorNoteRepository;
    private final ApplicationStatusRepository applicationStatusRepository;

    public ApplicationMapperImpl(ApplicationRepository applicationRepository, PatientRepository patientRepository, DoctorNoteRepository doctorNoteRepository, ApplicationStatusRepository applicationStatusRepository) {
        this.applicationRepository = applicationRepository;
        this.patientRepository = patientRepository;
        this.doctorNoteRepository = doctorNoteRepository;
        this.applicationStatusRepository = applicationStatusRepository;
    }

    @Override
    public Application toEntity(ApplicationDto dto) {
        return Application.builder()
                .id(dto.getId())
                .parentApplication(obtainParentApplication(dto.getParentApplicationId()))
                .patient(obtainPatient(dto.getPatientId()))
                .doctorNote(obtainDoctorNote(dto.getDoctorNoteId()))
                .status(obtainStatus(dto.getStatusId()))
                .finalDiagnosis(dto.getFinalDiagnosis())
                .build();
    }

    @Override
    public ApplicationDto toDto(Application entity) {
        return ApplicationDto.builder()
                .id(entity.getId())
                .parentApplicationId(obtainParentApplicationId(entity.getParentApplication()))
                .patientId(obtainPatientId(entity.getPatient()))
                .doctorNoteId(obtainDoctorNoteId(entity.getDoctorNote()))
                .statusId(obtainStatusId(entity.getStatus()))
                .finalDiagnosis(entity.getFinalDiagnosis())
                .build();
    }

    @Override
    public int obtainParentApplicationId(Application application) {
        return application.getParentApplication().getId();
    }

    @Override
    public Application obtainParentApplication(int applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            return applicationOptional.get();
        } else {
            throw new NotFoundException("Вложенного в обращение родительского обращения не существует");
        }
    }

    @Override
    public int obtainPatientId(Patient patient) {
        return patient.getId();
    }

    @Override
    public Patient obtainPatient(int patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            throw new NotFoundException("Вложенного в обращение пациента не существует");
        }
    }

    @Override
    public int obtainDoctorNoteId(DoctorNote doctorNote) {
        return doctorNote.getId();
    }

    @Override
    public DoctorNote obtainDoctorNote(int doctorNoteId) {
        Optional<DoctorNote> doctorNoteOptional = doctorNoteRepository.findById(doctorNoteId);
        if (doctorNoteOptional.isPresent()) {
            return doctorNoteOptional.get();
        } else {
            throw new NotFoundException("Вложенного в обращение направления не существует");
        }
    }

    @Override
    public int obtainStatusId(ApplicationStatus applicationStatus) {
        return applicationStatus.getId();
    }

    @Override
    public ApplicationStatus obtainStatus(int applicationStatusId) {
        Optional<ApplicationStatus> applicationStatusOptional = applicationStatusRepository.findById(applicationStatusId);
        if (applicationStatusOptional.isPresent()) {
            return applicationStatusOptional.get();
        } else {
            throw new NotFoundException("Вложенного в обращение статуса не существует");
        }
    }
}
