package ru.zalimannard.bachelorthesisserver.schema.schedule;

import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zalimannard.bachelorthesisserver.schema.application.ApplicationDto;
import ru.zalimannard.bachelorthesisserver.schema.application.ApplicationMapper;
import ru.zalimannard.bachelorthesisserver.schema.application.ApplicationService;
import ru.zalimannard.bachelorthesisserver.schema.doctor.DoctorDto;
import ru.zalimannard.bachelorthesisserver.schema.doctor.DoctorMapper;
import ru.zalimannard.bachelorthesisserver.schema.doctor.DoctorService;
import ru.zalimannard.bachelorthesisserver.schema.favor.FavorDto;
import ru.zalimannard.bachelorthesisserver.schema.favor.FavorMapper;
import ru.zalimannard.bachelorthesisserver.schema.favor.FavorService;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.ScheduleStatusDto;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.ScheduleStatusMapper;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.ScheduleStatusService;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ScheduleMapper {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;
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
    public abstract Schedule toEntity(ScheduleDto dto,
                                      @Context MappingType mappingType);

    @Mapping(target = "doctorId", source = "entity.doctor.id")
    @Mapping(target = "favorId", source = "entity.favor.id")
    @Mapping(target = "applicationId", source = "entity.application.id")
    @Mapping(target = "statusId", source = "entity.status.id")
    public abstract ScheduleDto toDto(Schedule entity);

    public abstract List<Schedule> toEntityList(List<ScheduleDto> dtoList,
                                                @Context MappingType mappingType);

    public abstract List<ScheduleDto> toDtoList(List<Schedule> entityList);

    @AfterMapping
    protected void toEntity(@MappingTarget Schedule entity, ScheduleDto dto,
                            @Context MappingType mappingType) {
        try {
            DoctorDto doctorDto = doctorService.read(dto.getDoctorId());
            entity.setDoctor(doctorMapper.toEntity(doctorDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getDoctorId()) == null) {
                // Всё нормально, поле doctor останется null
            } else {
                throw e;
            }
        }

        try {
            FavorDto favorDto = favorService.read(dto.getFavorId());
            entity.setFavor(favorMapper.toEntity(favorDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getFavorId()) == null) {
                // Всё нормально, поле favor останется null
            } else {
                throw e;
            }
        }

        try {
            ApplicationDto applicationDto = applicationService.read(dto.getApplicationId());
            entity.setApplication(applicationMapper.toEntity(applicationDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getApplicationId()) == null) {
                // Всё нормально, поле application останется null
            } else {
                throw e;
            }
        }

        try {
            ScheduleStatusDto scheduleStatusDto = scheduleStatusService.read(dto.getStatusId());
            entity.setStatus(scheduleStatusMapper.toEntity(scheduleStatusDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getStatusId()) == null) {
                // Всё нормально, поле status останется null
            } else {
                throw e;
            }
        }
    }

}
