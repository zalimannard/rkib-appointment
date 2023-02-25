package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.application.ApplicationRepository;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.favor.Favor;
import ru.zalimannard.bachelorthesisserver.favor.FavorRepository;

import java.util.List;

@Mapper
public interface UnscheduledVisitMapper {

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "favor", ignore = true)
    @Mapping(target = "application", ignore = true)
    UnscheduledVisit toEntity(UnscheduledVisitDto dto,
                              @Context DoctorRepository doctorRepository,
                              @Context FavorRepository favorRepository,
                              @Context ApplicationRepository applicationRepository);

    @Mapping(target = "doctorId", source = "entity.doctor.id")
    @Mapping(target = "favorId", source = "entity.favor.id")
    @Mapping(target = "applicationId", source = "entity.application.id")
    UnscheduledVisitDto toDto(UnscheduledVisit entity);

    List<UnscheduledVisit> toEntityList(List<UnscheduledVisitDto> dtoList,
                                        @Context DoctorRepository doctorRepository,
                                        @Context FavorRepository favorRepository,
                                        @Context ApplicationRepository applicationRepository);

    List<UnscheduledVisitDto> toDtoList(List<UnscheduledVisit> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget UnscheduledVisit entity, UnscheduledVisitDto dto,
                          @Context DoctorRepository doctorRepository,
                          @Context FavorRepository favorRepository,
                          @Context ApplicationRepository applicationRepository) {
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

        if (dto.getApplicationId() != null) {
            Application application = applicationRepository.findById(dto.getApplicationId())
                    .orElseThrow(() -> new NotFoundException("Application", "id", dto.getApplicationId()));
            entity.setApplication(application);
        }
    }
}
