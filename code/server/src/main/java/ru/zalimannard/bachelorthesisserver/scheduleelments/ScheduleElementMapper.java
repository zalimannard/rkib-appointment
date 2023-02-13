package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.favor.Favor;
import ru.zalimannard.bachelorthesisserver.favor.FavorRepository;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatus;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatusRepository;

import java.util.List;

@Mapper
public interface ScheduleElementMapper {

    ScheduleElement toEntity(ScheduleElementDto dto,
                             @Context DoctorRepository doctorRepository,
                             @Context FavorRepository favorRepository,
                             @Context ScheduleElementStatusRepository scheduleElementStatusRepository);

    @Mapping(target = "doctorId", source = "entity.doctor.id")
    @Mapping(target = "favorId", source = "entity.favor.id")
    @Mapping(target = "statusId", source = "entity.status.id")
    ScheduleElementDto toDto(ScheduleElement entity);

    List<ScheduleElement> toEntityList(List<ScheduleElementDto> dtoList);

    List<ScheduleElementDto> toDtoList(List<ScheduleElement> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget ScheduleElement entity, ScheduleElementDto dto,
                          @Context DoctorRepository doctorRepository,
                          @Context FavorRepository favorRepository,
                          @Context ScheduleElementStatusRepository scheduleElementStatusRepository) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Doctor", "id", dto.getDoctorId()));
        entity.setDoctor(doctor);

        Favor favor = favorRepository.findById(dto.getFavorId())
                .orElseThrow(() -> new NotFoundException("Favor", "id", dto.getFavorId()));
        entity.setFavor(favor);

        ScheduleElementStatus scheduleElementStatus = scheduleElementStatusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new NotFoundException("ScheduleElementStatus", "id", dto.getStatusId()));
        entity.setStatus(scheduleElementStatus);
    }
}
