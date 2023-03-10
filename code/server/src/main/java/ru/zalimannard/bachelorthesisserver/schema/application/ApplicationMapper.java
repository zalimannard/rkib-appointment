package ru.zalimannard.bachelorthesisserver.schema.application;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundExceptionHttp;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatusRepository;
import ru.zalimannard.bachelorthesisserver.schema.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.schema.doctornote.DoctorNoteRepository;
import ru.zalimannard.bachelorthesisserver.schema.patient.Patient;
import ru.zalimannard.bachelorthesisserver.schema.patient.PatientRepository;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctorNote", ignore = true)
    @Mapping(target = "status", ignore = true)
    Application toEntity(ApplicationDto dto,
                         @Context ApplicationRepository applicationRepository,
                         @Context PatientRepository patientRepository,
                         @Context DoctorNoteRepository doctorNoteRepository,
                         @Context ApplicationStatusRepository applicationStatusRepository);

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

        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new NotFoundExceptionHttp("Patient", "id", dto.getPatientId()));
            entity.setPatient(patient);
        }

        if (dto.getDoctorNoteId() != null) {
            DoctorNote doctorNote = doctorNoteRepository.findById(dto.getDoctorNoteId())
                    .orElseThrow(() -> new NotFoundExceptionHttp("DoctorNote", "id", dto.getDoctorNoteId()));
            entity.setDoctorNote(doctorNote);
        }

        if (dto.getStatusId() != null) {
            ApplicationStatus applicationStatus = applicationStatusRepository.findById(dto.getStatusId())
                    .orElseThrow(() -> new NotFoundExceptionHttp("ApplicationStatus", "id", dto.getStatusId()));
            entity.setStatus(applicationStatus);
        }
    }

}
