package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.springframework.stereotype.Component;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.application.ApplicationRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.scheduleelments.ScheduleElement;
import ru.zalimannard.bachelorthesisserver.scheduleelments.ScheduleElementRepository;

import java.util.Optional;

@Component
public class ScheduledVisitMapperImpl implements ScheduledVisitMapper {
    private final ScheduleElementRepository scheduleElementRepository;
    private final ApplicationRepository applicationRepository;

    public ScheduledVisitMapperImpl(ScheduleElementRepository scheduleElementRepository, ApplicationRepository applicationRepository) {
        this.scheduleElementRepository = scheduleElementRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public ScheduledVisit toEntity(ScheduledVisitDto dto) {
        return ScheduledVisit.builder()
                .id(dto.getId())
                .scheduleElement(obtainScheduleElement(dto.getScheduleElementId()))
                .application(obtainApplication(dto.getApplicationId()))
                .build();
    }

    @Override
    public ScheduledVisitDto toDto(ScheduledVisit entity) {
        return ScheduledVisitDto.builder()
                .id(entity.getId())
                .scheduleElementId(obtainScheduleElementId(entity.getScheduleElement()))
                .applicationId(obtainApplicationId(entity.getApplication()))
                .build();
    }

    private int obtainScheduleElementId(ScheduleElement scheduleElement) {
        return scheduleElement.getId();
    }

    private ScheduleElement obtainScheduleElement(int scheduleElementId) {
        Optional<ScheduleElement> scheduleElementOptional = scheduleElementRepository.findById(scheduleElementId);
        if (scheduleElementOptional.isPresent()) {
            return scheduleElementOptional.get();
        } else {
            throw new NotFoundException("Вложенного в запланированное пощещение элемента плана не существует");
        }
    }

    private int obtainApplicationId(Application application) {
        return application.getId();
    }

    private Application obtainApplication(int applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            return applicationOptional.get();
        } else {
            throw new NotFoundException("Вложенного в запланированное пощещение обращения не существует");
        }
    }
}
