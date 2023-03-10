package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleStatusServiceImpl implements ScheduleStatusService {

    private final ScheduleStatusRepository scheduleStatusRepository;
    private final ScheduleStatusMapper scheduleStatusMapper;

    @Override
    public ScheduleStatusDto create(ScheduleStatusDto scheduleStatusDto) {
        try {
            ScheduleStatus scheduleStatusRequest = scheduleStatusMapper.toEntity(scheduleStatusDto, MappingType.DEFAULT);
            ScheduleStatus scheduleStatusResponse = scheduleStatusRepository.save(scheduleStatusRequest);
            return scheduleStatusMapper.toDto(scheduleStatusResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.scheduleStatus}");
        }
    }

    @Override
    public ScheduleStatusDto read(String id) {
        ScheduleStatus scheduleStatus = scheduleStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHttp("${application.entityNames.scheduleStatus}", id));
        return scheduleStatusMapper.toDto(scheduleStatus);
    }

    @Override
    public List<ScheduleStatusDto> search(ScheduleStatusDto filterScheduleStatusDto, int pageNo, int pageSize, String[] sort) {
        ScheduleStatus filterScheduleStatus = scheduleStatusMapper.toEntity(filterScheduleStatusDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<ScheduleStatus> scheduleStatusList =
                scheduleStatusRepository.search(filterScheduleStatus.getType(), filterScheduleStatus.getName(),
                        pageable);
        return scheduleStatusMapper.toDtoList(scheduleStatusList);
    }

    @Override
    public ScheduleStatusDto update(String id, ScheduleStatusDto scheduleStatusDto) {
        read(id);
        try {
            ScheduleStatus scheduleStatusRequest = scheduleStatusMapper.toEntity(scheduleStatusDto, MappingType.DEFAULT);
            scheduleStatusRequest.setId(id);
            ScheduleStatus scheduleStatusResponse = scheduleStatusRepository.save(scheduleStatusRequest);
            return scheduleStatusMapper.toDto(scheduleStatusResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.scheduleStatus}");
        }
    }

    @Override
    public ScheduleStatusDto delete(String id) {
        try {
            ScheduleStatusDto scheduleStatusDto = read(id);
            scheduleStatusRepository.deleteById(id);
            return scheduleStatusDto;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.scheduleStatus}");
        }
    }

}
