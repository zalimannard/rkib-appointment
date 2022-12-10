package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduledVisitServiceImpl implements ScheduledVisitService {
    private final ScheduledVisitMapper scheduledVisitMapper;
    private final ScheduledVisitRepository scheduledVisitRepository;

    public ScheduledVisitServiceImpl(ScheduledVisitMapper scheduledVisitMapper, ScheduledVisitRepository scheduledVisitRepository) {
        this.scheduledVisitMapper = scheduledVisitMapper;
        this.scheduledVisitRepository = scheduledVisitRepository;
    }

    @Override
    public ScheduledVisitDto read(int id) {
        Optional<ScheduledVisit> scheduledVisitOptional = scheduledVisitRepository.findById(id);
        if (scheduledVisitOptional.isPresent()) {
            ScheduledVisit scheduledVisit = scheduledVisitOptional.get();
            return scheduledVisitMapper.toDto(scheduledVisit);
        } else {
            throw new NotFoundException("Запланированное посещение с id=" + id + " не найдено.");
        }
    }

    @Override
    public List<ScheduledVisitDto> list() {
        Iterable<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findAll();
        List<ScheduledVisitDto> scheduledVisitDtos = new ArrayList<>();
        scheduledVisits.forEach(scheduledVisit -> scheduledVisitDtos.add(scheduledVisitMapper.toDto(scheduledVisit)));
        return scheduledVisitDtos;
    }

    @Override
    public ScheduledVisitDto create(ScheduledVisitDto scheduledVisitDto) {
        ScheduledVisit scheduledVisit = scheduledVisitMapper.toEntity(scheduledVisitDto);
        ScheduledVisit addedScheduledVisit = scheduledVisitRepository.save(scheduledVisit);
        return scheduledVisitMapper.toDto(addedScheduledVisit);
    }

    @Override
    public ScheduledVisitDto update(ScheduledVisitDto scheduledVisitDto) {
        if (scheduledVisitRepository.existsById(scheduledVisitDto.getId())) {
            ScheduledVisit scheduledVisit = scheduledVisitMapper.toEntity(scheduledVisitDto);
            scheduledVisitRepository.save(scheduledVisit);
            return read(scheduledVisit.getId());
        } else {
            throw new NotFoundException("Запланированное посещение с id=" + scheduledVisitDto.getId() + " не найдено. Ничего не изменено.");
        }
    }

    @Override
    public ScheduledVisitDto delete(int id) {
        ScheduledVisitDto scheduledVisitDto = read(id);
        scheduledVisitRepository.deleteById(id);
        return scheduledVisitDto;
    }
}
