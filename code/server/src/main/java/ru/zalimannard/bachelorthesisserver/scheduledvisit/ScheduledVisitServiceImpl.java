package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduledVisitServiceImpl implements ScheduledVisitService {
    private final ScheduledVisitRepository scheduledVisitRepository;
    private final ScheduledVisitMapper scheduledVisitMapper = Mappers.getMapper(ScheduledVisitMapper.class);

    public ScheduledVisitServiceImpl(ScheduledVisitRepository scheduledVisitRepository) {
        this.scheduledVisitRepository = scheduledVisitRepository;
    }

    @Override
    public ScheduledVisitDto get(int id) {
        ScheduledVisit scheduledVisit = scheduledVisitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduledVisit", "id", String.valueOf(id)));
        return scheduledVisitMapper.toDto(scheduledVisit);
    }

    @Override
    public List<ScheduledVisitDto> list() {
        List<ScheduledVisit> scheduledVisitList = new ArrayList<>();
        scheduledVisitRepository.findAll().forEach(scheduledVisit -> scheduledVisitList.add(scheduledVisit));
        return scheduledVisitMapper.toDtoList(scheduledVisitList);
    }

    @Override
    public ScheduledVisitDto create(ScheduledVisitDto scheduledVisitDto) {
        ScheduledVisit scheduledVisitRequest = scheduledVisitMapper.toEntity(scheduledVisitDto);
        ScheduledVisit scheduledVisitResponse = scheduledVisitRepository.save(scheduledVisitRequest);
        return scheduledVisitMapper.toDto(scheduledVisitResponse);
    }

    @Override
    public ScheduledVisitDto update(ScheduledVisitDto scheduledVisitDto) {
        ScheduledVisit scheduledVisitRequest = scheduledVisitMapper.toEntity(scheduledVisitDto);
        if (scheduledVisitRepository.existsById(scheduledVisitRequest.getId())) {
            ScheduledVisit scheduledVisitResponse = scheduledVisitRepository.save(scheduledVisitRequest);
            return scheduledVisitMapper.toDto(scheduledVisitResponse);
        } else {
            throw new NotFoundException("ScheduledVisit", "id", String.valueOf(scheduledVisitRequest.getId()));
        }
    }

    @Override
    public ScheduledVisitDto delete(int id) {
        ScheduledVisit scheduledVisit = scheduledVisitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduledVisit", "id", String.valueOf(id)));
        scheduledVisitRepository.deleteById(id);
        return scheduledVisitMapper.toDto(scheduledVisit);
    }
}
