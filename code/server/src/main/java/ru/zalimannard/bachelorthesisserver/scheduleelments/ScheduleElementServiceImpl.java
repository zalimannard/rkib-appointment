package ru.zalimannard.bachelorthesisserver.scheduleelments;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleElementServiceImpl implements ScheduleElementService {
    private final ScheduleElementRepository scheduleElementRepository;
    private final ScheduleElementMapper scheduleElementMapper = Mappers.getMapper(ScheduleElementMapper.class);

    @Override
    public ScheduleElementDto get(String id) {
        ScheduleElement scheduleElement = scheduleElementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElement", "id", id));
        return scheduleElementMapper.toDto(scheduleElement);
    }

    @Override
    public List<ScheduleElementDto> list() {
        List<ScheduleElement> scheduleElementList = new ArrayList<>();
        scheduleElementRepository.findAll().forEach(scheduleElementList::add);
        return scheduleElementMapper.toDtoList(scheduleElementList);
    }

    @Override
    public ScheduleElementDto create(ScheduleElementDto scheduleElementDto) {
        ScheduleElement scheduleElementRequest = scheduleElementMapper.toEntity(scheduleElementDto);
        ScheduleElement scheduleElementResponse = scheduleElementRepository.save(scheduleElementRequest);
        return scheduleElementMapper.toDto(scheduleElementResponse);
    }

    @Override
    public ScheduleElementDto update(ScheduleElementDto scheduleElementDto) {
        ScheduleElement scheduleElementRequest = scheduleElementMapper.toEntity(scheduleElementDto);
        if (scheduleElementRepository.existsById(scheduleElementRequest.getId())) {
            ScheduleElement scheduleElementResponse = scheduleElementRepository.save(scheduleElementRequest);
            return scheduleElementMapper.toDto(scheduleElementResponse);
        } else {
            throw new NotFoundException("ScheduleElement", "id", String.valueOf(scheduleElementRequest.getId()));
        }
    }

    @Override
    public ScheduleElementDto delete(String id) {
        ScheduleElement scheduleElement = scheduleElementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElement", "id", id));
        scheduleElementRepository.deleteById(id);
        return scheduleElementMapper.toDto(scheduleElement);
    }
}
