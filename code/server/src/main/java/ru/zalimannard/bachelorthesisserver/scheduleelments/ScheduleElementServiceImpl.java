package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleElementServiceImpl implements ScheduleElementService {
    private final ScheduleElementMapper scheduleElementMapper;
    private final ScheduleElementRepository scheduleElementRepository;

    public ScheduleElementServiceImpl(ScheduleElementMapper scheduleElementMapper, ScheduleElementRepository scheduleElementRepository) {
        this.scheduleElementMapper = scheduleElementMapper;
        this.scheduleElementRepository = scheduleElementRepository;
    }

    @Override
    public ScheduleElementDto create(int id) {
        Optional<ScheduleElement> scheduleElementOptional = scheduleElementRepository.findById(id);
        if (scheduleElementOptional.isPresent()) {
            ScheduleElement scheduleElement = scheduleElementOptional.get();
            return scheduleElementMapper.toDto(scheduleElement);
        } else {
            throw new NotFoundException("Элемент расписания с id=" + id + " не найден.");
        }
    }

    @Override
    public List<ScheduleElementDto> list() {
        Iterable<ScheduleElement> scheduleElements = scheduleElementRepository.findAll();
        List<ScheduleElementDto> scheduleElementDtos = new ArrayList<>();
        scheduleElements.forEach(scheduleElement -> scheduleElementDtos.add(scheduleElementMapper.toDto(scheduleElement)));
        return scheduleElementDtos;
    }

    @Override
    public ScheduleElementDto create(ScheduleElementDto scheduleElementDto) {
        ScheduleElement scheduleElement = scheduleElementMapper.toEntity(scheduleElementDto);
        ScheduleElement addedScheduleElement = scheduleElementRepository.save(scheduleElement);
        return scheduleElementMapper.toDto(addedScheduleElement);
    }

    @Override
    public ScheduleElementDto update(ScheduleElementDto scheduleElementDto) {
        if (scheduleElementRepository.existsById(scheduleElementDto.getId())) {
            ScheduleElement scheduleElement = scheduleElementMapper.toEntity(scheduleElementDto);
            scheduleElementRepository.save(scheduleElement);
            return create(scheduleElement.getId());
        } else {
            throw new NotFoundException("Элемент расписания с id=" + scheduleElementDto.getId() + " не найден. Ничего не изменено.");
        }
    }

    @Override
    public ScheduleElementDto delete(int id) {
        ScheduleElementDto doctorNoteDto = create(id);
        scheduleElementRepository.deleteById(id);
        return doctorNoteDto;
    }
}
