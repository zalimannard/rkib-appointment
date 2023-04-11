package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.Utils;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper mapper;
    private final ScheduleRepository repository;

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        Schedule request = mapper.toEntity(scheduleDto);

        Schedule response = createEntity(request);

        return mapper.toDto(response);
    }

    @Override
    public Schedule createEntity(Schedule schedule) {
        try {
            return repository.save(schedule);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("scs-01", "schedule", e.getLocalizedMessage());
        }
    }


    @Override
    public ScheduleDto read(String id) {
        Schedule response = readEntity(id);

        return mapper.toDto(response);
    }


    @Override
    public Schedule readEntity(String id) {
        Optional<Schedule> responseOptional = repository.findById(id);
        if (responseOptional.isPresent()) {
            return responseOptional.get();
        } else {
            throw new NotFoundException("scs-02", "id", id);
        }
    }

    @Override
    public List<ScheduleDto> search(ScheduleDto filterDto, Date beginTimestamp, Date endTimestamp, String[] sortBy,
                                    int pageSize, int pageNumber) {
        Schedule filter = mapper.toEntity(filterDto);

        List<Schedule> response = searchEntities(filter, beginTimestamp, endTimestamp, sortBy, pageSize, pageNumber);

        return mapper.toDtoList(response);
    }

    @Override
    public List<Schedule> searchEntities(Schedule filter, Date beginTimestamp, Date endTimestamp, String[] sortBy,
                                         int pageSize, int pageNumber) {
        List<Sort.Order> orders = Utils.ordersByStringArray(sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        return repository.search(
                filter.getDoctor(),
                filter.getFavor(),
                filter.getApplication(),
                filter.getStatus(),
                filter.getCommentary(),
                beginTimestamp,
                endTimestamp,
                pageable);
    }

    @Override
    public List<Schedule> searchEntities(Schedule filter, Date beginTimestamp, Date endTimestamp,
                                         int pageSize, int pageNumber) {
        String sortByFromProperties = "${schedule.constant.defaultSort}";
        String[] sortBy;
        try {
            sortBy = sortByFromProperties.split(",");
        } catch (NullPointerException e) {
            throw new NotFoundException("scs-03", "defaultSort", null);
        }
        return searchEntities(filter, beginTimestamp, endTimestamp, sortBy, pageSize, pageNumber);
    }


    @Override
    public ScheduleDto update(String id, ScheduleDto scheduleDto) {
        Schedule request = mapper.toEntity(scheduleDto);

        Schedule response = updateEntity(id, request);

        return mapper.toDto(response);
    }

    @Override
    public Schedule updateEntity(String id, Schedule schedule) {
        if (repository.existsById(id)) {
            schedule.setId(id);
            return repository.save(schedule);
        } else {
            throw new NotFoundException("scs-04", "id", id);
        }
    }


    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("scs-05", "id", id);
        }
    }

}
