package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleElementStatusServiceImpl implements ScheduleElementStatusService {
    private final ScheduleElementStatusRepository scheduleElementStatusRepository;
    private final ScheduleElementStatusMapper scheduleElementStatusMapper = Mappers.getMapper(ScheduleElementStatusMapper.class);

    @Override
    public ScheduleElementStatusDto get(String id) {
        ScheduleElementStatus scheduleElementStatus = scheduleElementStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElementStatus", "id", id));
        return scheduleElementStatusMapper.toDto(scheduleElementStatus);
    }

    @Override
    public List<ScheduleElementStatusDto> list() {
        List<ScheduleElementStatus> scheduleElementStatusList = new ArrayList<>();
        scheduleElementStatusRepository.findAll().forEach(scheduleElementStatusList::add);
        return scheduleElementStatusMapper.toDtoList(scheduleElementStatusList);
    }

    @Override
    public ScheduleElementStatusDto create(ScheduleElementStatusDto scheduleElementStatusDto) {
        ScheduleElementStatus scheduleElementStatusRequest = scheduleElementStatusMapper.toEntity(scheduleElementStatusDto);
        ScheduleElementStatus scheduleElementStatusResponse = scheduleElementStatusRepository.save(scheduleElementStatusRequest);
        return scheduleElementStatusMapper.toDto(scheduleElementStatusResponse);
    }

    @Override
    public ScheduleElementStatusDto update(ScheduleElementStatusDto scheduleElementStatusDto) {
        ScheduleElementStatus scheduleElementStatusRequest = scheduleElementStatusMapper.toEntity(scheduleElementStatusDto);
        if (scheduleElementStatusRepository.existsById(scheduleElementStatusRequest.getId())) {
            ScheduleElementStatus scheduleElementStatusResponse = scheduleElementStatusRepository.save(scheduleElementStatusRequest);
            return scheduleElementStatusMapper.toDto(scheduleElementStatusResponse);
        } else {
            throw new NotFoundException("ScheduleElementStatus", "id", String.valueOf(scheduleElementStatusRequest.getId()));
        }
    }

    @Override
    public ScheduleElementStatusDto delete(String id) {
        ScheduleElementStatus scheduleElementStatus = scheduleElementStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElementStatus", "id", id));
        scheduleElementStatusRepository.deleteById(id);
        return scheduleElementStatusMapper.toDto(scheduleElementStatus);
    }
}
