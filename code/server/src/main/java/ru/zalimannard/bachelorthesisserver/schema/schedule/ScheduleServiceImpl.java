package ru.zalimannard.bachelorthesisserver.schema.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.DataIntegrityViolationExceptionHttp;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundExceptionHttp;
import ru.zalimannard.bachelorthesisserver.utils.Utils;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        try {
            Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto, MappingType.DEFAULT);
            Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
            return scheduleMapper.toDto(scheduleResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.schedule}");
        }
    }

    @Override
    public ScheduleDto read(String id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHttp("${application.entityNames.schedule}", id));
        return scheduleMapper.toDto(schedule);
    }

    @Override
    public List<ScheduleDto> search(ScheduleDto filterScheduleDto, Date beginTimestamp, Date endTimestamp, int pageNo, int pageSize, String[] sort) {
        Schedule filterSchedule = scheduleMapper.toEntity(filterScheduleDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<Schedule> scheduleList = scheduleRepository.search(filterSchedule.getDoctor(), filterSchedule.getFavor(),
                filterSchedule.getApplication(), filterSchedule.getStatus(), beginTimestamp, endTimestamp,
                filterSchedule.getCommentary(), pageable);
        return scheduleMapper.toDtoList(scheduleList);
    }

    @Override
    public ScheduleDto update(String id, ScheduleDto scheduleDto) {
        read(id);
        try {
            Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto, MappingType.DEFAULT);
            scheduleRequest.setId(id);
            Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
            return scheduleMapper.toDto(scheduleResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.schedule}");
        }
    }

    @Override
    public ScheduleDto delete(String id) {
        try {
            ScheduleDto scheduleDto = read(id);
            scheduleRepository.deleteById(id);
            return scheduleDto;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.schedule}");
        }
    }

}
