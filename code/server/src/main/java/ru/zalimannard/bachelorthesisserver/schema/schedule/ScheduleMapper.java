package ru.zalimannard.bachelorthesisserver.schema.schedule;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.schema.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.schema.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.schema.favor.Favor;
import ru.zalimannard.bachelorthesisserver.schema.favor.FavorRepository;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.ScheduleStatus;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.ScheduleStatusRepository;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "status", ignore = true)
    Schedule toEntity(ScheduleDto dto,
                      @Context DoctorRepository doctorRepository,
                      @Context FavorRepository favorRepository,
                      @Context ScheduleStatusRepository scheduleStatusRepository);

    @Mapping(target = "doctorId", source = "entity.doctor.id")
    @Mapping(target = "favorId", source = "entity.favor.id")
    @Mapping(target = "statusId", source = "entity.status.id")
    ScheduleDto toDto(Schedule entity);

    List<Schedule> toEntityList(List<ScheduleDto> dtoList,
                                @Context DoctorRepository doctorRepository,
                                @Context FavorRepository favorRepository,
                                @Context ScheduleStatusRepository scheduleStatusRepository);

    List<ScheduleDto> toDtoList(List<Schedule> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget Schedule entity, ScheduleDto dto,
                          @Context DoctorRepository doctorRepository,
                          @Context FavorRepository favorRepository,
                          @Context ScheduleStatusRepository scheduleStatusRepository) {
        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new NotFoundException("Doctor", "id", dto.getDoctorId()));
            entity.setDoctor(doctor);
        }

        if (dto.getFavorId() != null) {
            Favor favor = favorRepository.findById(dto.getFavorId())
                    .orElseThrow(() -> new NotFoundException("Favor", "id", dto.getFavorId()));
            entity.setFavor(favor);
        }

        if (dto.getStatusId() != null) {
            ScheduleStatus scheduleStatus = scheduleStatusRepository.findById(dto.getStatusId())
                    .orElseThrow(() -> new NotFoundException("ScheduleElementStatus", "id", dto.getStatusId()));
            entity.setStatus(scheduleStatus);
        }
    }

}
