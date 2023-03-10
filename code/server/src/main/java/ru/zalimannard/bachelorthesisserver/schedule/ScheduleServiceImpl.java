package ru.zalimannard.bachelorthesisserver.schedule;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.favor.FavorRepository;
import ru.zalimannard.bachelorthesisserver.schedule.status.ScheduleStatusRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;
    private final FavorRepository favorRepository;
    private final ScheduleStatusRepository scheduleStatusRepository;
    private final ScheduleMapper scheduleMapper = Mappers.getMapper(ScheduleMapper.class);

    @Override
    public ScheduleDto get(String id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElement", "id", id));
        return scheduleMapper.toDto(schedule);
    }

    @Override
    public List<ScheduleDto> list(ScheduleDto exampleScheduleDto) {
        Schedule exampleSchedule = scheduleMapper.toEntity(exampleScheduleDto,
                doctorRepository, favorRepository, scheduleStatusRepository);
        List<Schedule> scheduleList = new ArrayList<>(scheduleRepository.findAll(Example.of(exampleSchedule)));
        return scheduleMapper.toDtoList(scheduleList);
    }

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto, doctorRepository,
                favorRepository, scheduleStatusRepository);
        Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
        return scheduleMapper.toDto(scheduleResponse);
    }

    @Override
    public ScheduleDto update(ScheduleDto scheduleDto) {
        Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto, doctorRepository,
                favorRepository, scheduleStatusRepository);
        if (scheduleRepository.existsById(scheduleRequest.getId())) {
            Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
            return scheduleMapper.toDto(scheduleResponse);
        } else {
            throw new NotFoundException("ScheduleElement", "id", String.valueOf(scheduleRequest.getId()));
        }
    }

    @Override
    public ScheduleDto delete(String id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ScheduleElement", "id", id));
        scheduleRepository.deleteById(id);
        return scheduleMapper.toDto(schedule);
    }
}
