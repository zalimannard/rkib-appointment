package ru.zalimannard.bachelorthesisserver.application;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatusRepository;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNoteDto;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNoteRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.institution.Institution;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionRepository;
import ru.zalimannard.bachelorthesisserver.patient.Patient;
import ru.zalimannard.bachelorthesisserver.patient.PatientRepository;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    Application toEntity(ApplicationDto dto, ApplicationRepository applicationRepository,
                         PatientRepository patientRepository,
                         DoctorNoteRepository doctorNoteRepository,
                         ApplicationStatusRepository applicationStatusRepository);

    @Mapping(target = "patientId", source = "entity.patient.id")
    @Mapping(target = "doctorNoteId", source = "entity.doctorNote.id")
    @Mapping(target = "statusId", source = "entity.status.id")
    ApplicationDto toDto(Application entity);

    List<Application> toEntityList(List<ApplicationDto> dtoList);

    List<ApplicationDto> toDtoList(List<Application> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget Application entity, ApplicationDto dto,
                          ApplicationRepository applicationRepository,
                          PatientRepository patientRepository,
                          DoctorNoteRepository doctorNoteRepository,
                          ApplicationStatusRepository applicationStatusRepository) {
        if (dto.getParentApplicationId() != null) {
            Application parentApplicationEntity = applicationRepository.findById(dto.getParentApplicationId())
                    .orElseThrow(() -> new NotFoundException("Application", "id", dto.getParentApplicationId()));
            entity.setParentApplication(parentApplicationEntity);
        }

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient", "id", dto.getPatientId()));
        entity.setPatient(patient);

        DoctorNote doctorNote = doctorNoteRepository.findById(dto.getDoctorNoteId())
                .orElseThrow(() -> new NotFoundException("DoctorNote", "id", dto.getDoctorNoteId()));
        entity.setDoctorNote(doctorNote);

        ApplicationStatus applicationStatus = applicationStatusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new NotFoundException("ApplicationStatus", "id", dto.getStatusId()));
        entity.setStatus(applicationStatus);
    }

    @AfterMapping
    default void toDto(@MappingTarget ApplicationDto dto, Application entity) {
        if (entity.getParentApplication() != null) {
            dto.setParentApplicationId(entity.getParentApplication().getId());
        }
    }
}
