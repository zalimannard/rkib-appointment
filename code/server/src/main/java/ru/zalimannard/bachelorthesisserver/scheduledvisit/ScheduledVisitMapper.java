package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.application.ApplicationRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.scheduleelments.ScheduleElement;
import ru.zalimannard.bachelorthesisserver.scheduleelments.ScheduleElementRepository;

import java.util.List;

@Mapper
public interface ScheduledVisitMapper {

    ScheduledVisit toEntity(ScheduledVisitDto dto,
                            @Context ScheduleElementRepository scheduleElementRepository,
                            @Context ApplicationRepository applicationRepository);

    @Mapping(target = "scheduleElementId", source = "entity.scheduleElement.id")
    @Mapping(target = "applicationId", source = "entity.application.id")
    ScheduledVisitDto toDto(ScheduledVisit entity);

    List<ScheduledVisit> toEntityList(List<ScheduledVisitDto> dtoList);

    List<ScheduledVisitDto> toDtoList(List<ScheduledVisit> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget ScheduledVisit entity, ScheduledVisitDto dto,
                          @Context ScheduleElementRepository scheduleElementRepository,
                          @Context ApplicationRepository applicationRepository) {
        ScheduleElement scheduleElement = scheduleElementRepository.findById(dto.getScheduleElementId())
                .orElseThrow(() -> new NotFoundException("ScheduleElement", "id", dto.getScheduleElementId()));
        entity.setScheduleElement(scheduleElement);

        Application application = applicationRepository.findById(dto.getApplicationId())
                .orElseThrow(() -> new NotFoundException("Application", "id", dto.getApplicationId()));
        entity.setApplication(application);
    }
}
