package ru.zalimannard.rkibappointmentbackend.schema.application;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zalimannard.rkibappointmentbackend.schema.application.status.ApplicationStatus;
import ru.zalimannard.rkibappointmentbackend.schema.application.status.ApplicationStatusMapper;
import ru.zalimannard.rkibappointmentbackend.schema.application.status.ApplicationStatusService;
import ru.zalimannard.rkibappointmentbackend.schema.institution.Institution;
import ru.zalimannard.rkibappointmentbackend.schema.institution.InstitutionMapper;
import ru.zalimannard.rkibappointmentbackend.schema.institution.InstitutionService;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private InstitutionMapper institutionMapper;
    @Autowired
    private ApplicationStatusService applicationStatusService;
    @Autowired
    private ApplicationStatusMapper applicationStatusMapper;

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "sendingInstitution", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract Application toEntity(ApplicationDto dto);

    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "sendingInstitutionId", ignore = true)
    @Mapping(target = "statusId", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "sendingInstitution", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract ApplicationDto toDto(Application entity);

    public abstract List<Application> toEntityList(List<ApplicationDto> dto);

    public abstract List<ApplicationDto> toDtoList(List<Application> entity);

    @AfterMapping
    protected final void toEntity(@MappingTarget Application entity, ApplicationDto dto) {
        if (dto.getPatientId() != null) {
            Person patient = personService.readEntity(dto.getPatientId());
            entity.setPatient(patient);
        }

        if (dto.getSendingInstitutionId() != null) {
            Institution institution = institutionService.readEntity(dto.getSendingInstitutionId());
            entity.setSendingInstitution(institution);
        }

        if (dto.getStatusId() != null) {
            ApplicationStatus status = applicationStatusService.readEntity(dto.getStatusId());
            entity.setStatus(status);
        }
    }

    @AfterMapping
    protected void toDto(@MappingTarget ApplicationDto dto, Application entity) {
        dto.setPatient(personMapper.toDto(entity.getPatient()));
        dto.setSendingInstitution(institutionMapper.toDto(entity.getSendingInstitution()));
        dto.setStatus(applicationStatusMapper.toDto(entity.getStatus()));
    }

}
