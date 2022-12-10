package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleElementStatusServiceImpl implements ScheduleElementStatusService {
    private final ScheduleElementStatusMapper scheduleElementStatusMapper;
    private final ScheduleElementStatusRepository scheduleElementStatusRepository;

    public ScheduleElementStatusServiceImpl(ScheduleElementStatusMapper scheduleElementStatusMapper, ScheduleElementStatusRepository scheduleElementStatusRepository) {
        this.scheduleElementStatusMapper = scheduleElementStatusMapper;
        this.scheduleElementStatusRepository = scheduleElementStatusRepository;
    }

    @Override
    public ScheduleElementStatusDto read(int id) {
        Optional<ScheduleElementStatus> scheduleElementStatusOptional = scheduleElementStatusRepository.findById(id);
        if (scheduleElementStatusOptional.isPresent()) {
            ScheduleElementStatus scheduleElementStatus = scheduleElementStatusOptional.get();
            return scheduleElementStatusMapper.toDto(scheduleElementStatus);
        } else {
            throw new NotFoundException("Статус элемента расписания с id=" + id + " не найден.");
        }
    }

    @Override
    public List<ScheduleElementStatusDto> list() {
        Iterable<ScheduleElementStatus> scheduleElementStatuses = scheduleElementStatusRepository.findAll();
        List<ScheduleElementStatusDto> scheduleElementStatusDtos = new ArrayList<>();
        scheduleElementStatuses.forEach(scheduleElementStatus -> scheduleElementStatusDtos.add(scheduleElementStatusMapper.toDto(scheduleElementStatus)));
        return scheduleElementStatusDtos;
    }

    @Override
    public ScheduleElementStatusDto create(ScheduleElementStatusDto scheduleElementStatusDto) {
        ScheduleElementStatus scheduleElementStatus = scheduleElementStatusMapper.toEntity(scheduleElementStatusDto);
        ScheduleElementStatus addedScheduleElementStatus = scheduleElementStatusRepository.save(scheduleElementStatus);
        return scheduleElementStatusMapper.toDto(addedScheduleElementStatus);
    }

    @Override
    public ScheduleElementStatusDto update(ScheduleElementStatusDto scheduleElementStatusDto) {
        if (scheduleElementStatusRepository.existsById(scheduleElementStatusDto.getId())) {
            ScheduleElementStatus scheduleElementStatus = scheduleElementStatusMapper.toEntity(scheduleElementStatusDto);
            scheduleElementStatusRepository.save(scheduleElementStatus);
            return read(scheduleElementStatus.getId());
        } else {
            throw new NotFoundException("Статус элемента расписания с id=" + scheduleElementStatusDto.getId() + " не найден. Ничего не изменено.");
        }
    }

    @Override
    public ScheduleElementStatusDto delete(int id) {
        ScheduleElementStatusDto scheduleElementStatusDto = read(id);
        scheduleElementStatusRepository.deleteById(id);
        return scheduleElementStatusDto;
    }
}
