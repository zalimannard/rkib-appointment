package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleStatusServiceImpl implements ScheduleStatusService {

    private final ScheduleStatusRepository scheduleStatusRepository;
    private final ScheduleStatusMapper scheduleStatusMapper = Mappers.getMapper(ScheduleStatusMapper.class);

    @Override
    public ScheduleStatusDto get(String id) {
        ScheduleStatus scheduleStatus = scheduleStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElementStatus", "id", id));
        return scheduleStatusMapper.toDto(scheduleStatus);
    }

    @Override
    public List<ScheduleStatusDto> list(ScheduleStatusDto exampleScheduleStatusDto) {
        ScheduleStatus exampleScheduleStatus = scheduleStatusMapper.toEntity(exampleScheduleStatusDto);
        List<ScheduleStatus> scheduleStatusList = new ArrayList<>(scheduleStatusRepository.findAll(Example.of(exampleScheduleStatus)));
        return scheduleStatusMapper.toDtoList(scheduleStatusList);
    }

    @Override
    public ScheduleStatusDto create(ScheduleStatusDto scheduleStatusDto) {
        ScheduleStatus scheduleStatusRequest = scheduleStatusMapper.toEntity(scheduleStatusDto);
        ScheduleStatus scheduleStatusResponse = scheduleStatusRepository.save(scheduleStatusRequest);
        return scheduleStatusMapper.toDto(scheduleStatusResponse);
    }

    @Override
    public ScheduleStatusDto update(ScheduleStatusDto scheduleStatusDto) {
        ScheduleStatus scheduleStatusRequest = scheduleStatusMapper.toEntity(scheduleStatusDto);
        if (scheduleStatusRepository.existsById(scheduleStatusRequest.getId())) {
            ScheduleStatus scheduleStatusResponse = scheduleStatusRepository.save(scheduleStatusRequest);
            return scheduleStatusMapper.toDto(scheduleStatusResponse);
        } else {
            throw new NotFoundException("ScheduleElementStatus", "id", String.valueOf(scheduleStatusRequest.getId()));
        }
    }

    @Override
    public ScheduleStatusDto delete(String id) {
        ScheduleStatus scheduleStatus = scheduleStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElementStatus", "id", id));
        scheduleStatusRepository.deleteById(id);
        return scheduleStatusMapper.toDto(scheduleStatus);
    }

}
