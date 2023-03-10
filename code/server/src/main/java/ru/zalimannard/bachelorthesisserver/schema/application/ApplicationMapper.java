package ru.zalimannard.bachelorthesisserver.schema.application;

import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatusDto;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatusMapper;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatusService;
import ru.zalimannard.bachelorthesisserver.schema.institution.InstitutionDto;
import ru.zalimannard.bachelorthesisserver.schema.institution.InstitutionMapper;
import ru.zalimannard.bachelorthesisserver.schema.institution.InstitutionService;
import ru.zalimannard.bachelorthesisserver.schema.patient.PatientDto;
import ru.zalimannard.bachelorthesisserver.schema.patient.PatientMapper;
import ru.zalimannard.bachelorthesisserver.schema.patient.PatientService;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ApplicationMapper {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientMapper patientMapper;
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
    public abstract Application toEntity(ApplicationDto dto,
                                         @Context MappingType mappingType);

    @Mapping(target = "patientId", source = "entity.patient.id")
    @Mapping(target = "sendingInstitutionId", source = "entity.sendingInstitution.id")
    @Mapping(target = "statusId", source = "entity.status.id")
    public abstract ApplicationDto toDto(Application entity);

    public abstract List<Application> toEntityList(List<ApplicationDto> dtoList,
                                                   @Context MappingType mappingType);

    public abstract List<ApplicationDto> toDtoList(List<Application> entityList);

    @AfterMapping
    protected void toEntity(@MappingTarget Application entity, ApplicationDto dto,
                            @Context MappingType mappingType) {
        try {
            PatientDto patientDto = patientService.read(dto.getPatientId());
            entity.setPatient(patientMapper.toEntity(patientDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getPatientId()) == null) {
                // Всё нормально, поле patient останется null
            } else {
                throw e;
            }
        }

        try {
            InstitutionDto institutionDto = institutionService.read(dto.getSendingInstitutionId());
            entity.setSendingInstitution(institutionMapper.toEntity(institutionDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getSendingInstitutionId()) == null) {
                // Всё нормально, поле institution останется null
            } else {
                throw e;
            }
        }

        try {
            ApplicationStatusDto applicationStatusDto = applicationStatusService.read(dto.getStatusId());
            entity.setStatus(applicationStatusMapper.toEntity(applicationStatusDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getStatusId()) == null) {
                // Всё нормально, поле status останется null
            } else {
                throw e;
            }
        }
    }

}
