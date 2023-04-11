package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zalimannard.rkibappointmentbackend.schema.application.Application;
import ru.zalimannard.rkibappointmentbackend.schema.application.ApplicationMapper;
import ru.zalimannard.rkibappointmentbackend.schema.application.ApplicationService;
import ru.zalimannard.rkibappointmentbackend.schema.favor.Favor;
import ru.zalimannard.rkibappointmentbackend.schema.favor.FavorMapper;
import ru.zalimannard.rkibappointmentbackend.schema.favor.FavorService;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatus;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatusMapper;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatusService;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private FavorService favorService;
    @Autowired
    private FavorMapper favorMapper;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private ScheduleStatusService scheduleStatusService;
    @Autowired
    private ScheduleStatusMapper scheduleStatusMapper;

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract Schedule toEntity(ScheduleDto dto);

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "doctorId", ignore = true)
    @Mapping(target = "favorId", ignore = true)
    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "statusId", ignore = true)
    public abstract ScheduleDto toDto(Schedule entity);

    public abstract List<Schedule> toEntityList(List<ScheduleDto> dto);

    public abstract List<ScheduleDto> toDtoList(List<Schedule> entity);

    @AfterMapping
    protected final void toEntity(@MappingTarget Schedule entity, ScheduleDto dto) {
        if (dto.getDoctorId() != null) {
            Person doctor = personService.readEntity(dto.getDoctorId());
            entity.setDoctor(doctor);
        }

        if (dto.getFavorId() != null) {
            Favor favor = favorService.readEntity(dto.getFavorId());
            entity.setFavor(favor);
        }

        if (dto.getApplicationId() != null) {
            Application application = applicationService.readEntity(dto.getApplicationId());
            entity.setApplication(application);
        }

        if (dto.getStatusId() != null) {
            ScheduleStatus status = scheduleStatusService.readEntity(dto.getStatusId());
            entity.setStatus(status);
        }
    }

    @AfterMapping
    protected void toDto(@MappingTarget ScheduleDto dto, Schedule entity) {
        dto.setDoctor(personMapper.toDto(entity.getDoctor()));
        dto.setFavor(favorMapper.toDto(entity.getFavor()));
        dto.setApplication(applicationMapper.toDto(entity.getApplication()));
        dto.setStatus(scheduleStatusMapper.toDto(entity.getStatus()));
    }
}
