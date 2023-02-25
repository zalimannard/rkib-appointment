package ru.zalimannard.bachelorthesisserver.application;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatusRepository;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNoteRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.patient.Patient;
import ru.zalimannard.bachelorthesisserver.patient.PatientRepository;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    @Mapping(target = "parentApplication", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctorNote", ignore = true)
    @Mapping(target = "status", ignore = true)
    Application toEntity(ApplicationDto dto,
                         @Context ApplicationRepository applicationRepository,
                         @Context PatientRepository patientRepository,
                         @Context DoctorNoteRepository doctorNoteRepository,
                         @Context ApplicationStatusRepository applicationStatusRepository);

    @Mapping(target = "parentApplicationId", ignore = true)
    @Mapping(target = "patientId", source = "entity.patient.id")
    @Mapping(target = "doctorNoteId", source = "entity.doctorNote.id")
    @Mapping(target = "statusId", source = "entity.status.id")
    ApplicationDto toDto(Application entity);

    List<Application> toEntityList(List<ApplicationDto> dtoList,
                                   @Context ApplicationRepository applicationRepository,
                                   @Context PatientRepository patientRepository,
                                   @Context DoctorNoteRepository doctorNoteRepository,
                                   @Context ApplicationStatusRepository applicationStatusRepository);

    List<ApplicationDto> toDtoList(List<Application> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget Application entity, ApplicationDto dto,
                          @Context ApplicationRepository applicationRepository,
                          @Context PatientRepository patientRepository,
                          @Context DoctorNoteRepository doctorNoteRepository,
                          @Context ApplicationStatusRepository applicationStatusRepository) {
        if (dto.getParentApplicationId() != null) {
            Application parentApplicationEntity = applicationRepository.findById(dto.getParentApplicationId())
                    .orElseThrow(() -> new NotFoundException("Application", "id", dto.getParentApplicationId()));
            entity.setParentApplication(parentApplicationEntity);
        }

        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new NotFoundException("Patient", "id", dto.getPatientId()));
            entity.setPatient(patient);
        }

        if (dto.getDoctorNoteId() != null) {
            DoctorNote doctorNote = doctorNoteRepository.findById(dto.getDoctorNoteId())
                    .orElseThrow(() -> new NotFoundException("DoctorNote", "id", dto.getDoctorNoteId()));
            entity.setDoctorNote(doctorNote);
        }

        if (dto.getStatusId() != null) {
            ApplicationStatus applicationStatus = applicationStatusRepository.findById(dto.getStatusId())
                    .orElseThrow(() -> new NotFoundException("ApplicationStatus", "id", dto.getStatusId()));
            entity.setStatus(applicationStatus);
        }
    }

    @AfterMapping
    default void toDto(@MappingTarget ApplicationDto dto, Application entity) {
        if (entity.getParentApplication() != null) {
            dto.setParentApplicationId(entity.getParentApplication().getId());
        }
    }
}
