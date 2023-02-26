package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.application.ApplicationRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.scheduleelments.ScheduleElementRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledVisitServiceImpl implements ScheduledVisitService {
    private final ScheduledVisitRepository scheduledVisitRepository;
    private final ScheduleElementRepository scheduleElementRepository;
    private final ApplicationRepository applicationRepository;
    private final ScheduledVisitMapper scheduledVisitMapper = Mappers.getMapper(ScheduledVisitMapper.class);

    @Override
    public ScheduledVisitDto get(String id) {
        ScheduledVisit scheduledVisit = scheduledVisitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduledVisit", "id", id));
        return scheduledVisitMapper.toDto(scheduledVisit);
    }

    @Override
    public List<ScheduledVisitDto> list(ScheduledVisitDto exampleScheduledVisitDto) {
        ScheduledVisit exampleScheduledVisit = scheduledVisitMapper.toEntity(exampleScheduledVisitDto, scheduleElementRepository, applicationRepository);
        List<ScheduledVisit> scheduledVisitList = new ArrayList<>(scheduledVisitRepository.findAll(Example.of(exampleScheduledVisit)));
        return scheduledVisitMapper.toDtoList(scheduledVisitList);
    }

    @Override
    public ScheduledVisitDto create(ScheduledVisitDto scheduledVisitDto) {
        ScheduledVisit scheduledVisitRequest = scheduledVisitMapper.toEntity(scheduledVisitDto,
                scheduleElementRepository, applicationRepository);
        ScheduledVisit scheduledVisitResponse = scheduledVisitRepository.save(scheduledVisitRequest);
        return scheduledVisitMapper.toDto(scheduledVisitResponse);
    }

    @Override
    public ScheduledVisitDto update(ScheduledVisitDto scheduledVisitDto) {
        ScheduledVisit scheduledVisitRequest = scheduledVisitMapper.toEntity(scheduledVisitDto,
                scheduleElementRepository, applicationRepository);
        if (scheduledVisitRepository.existsById(scheduledVisitRequest.getId())) {
            ScheduledVisit scheduledVisitResponse = scheduledVisitRepository.save(scheduledVisitRequest);
            return scheduledVisitMapper.toDto(scheduledVisitResponse);
        } else {
            throw new NotFoundException("ScheduledVisit", "id", String.valueOf(scheduledVisitRequest.getId()));
        }
    }

    @Override
    public ScheduledVisitDto delete(String id) {
        ScheduledVisit scheduledVisit = scheduledVisitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduledVisit", "id", id));
        scheduledVisitRepository.deleteById(id);
        return scheduledVisitMapper.toDto(scheduledVisit);
    }
}
