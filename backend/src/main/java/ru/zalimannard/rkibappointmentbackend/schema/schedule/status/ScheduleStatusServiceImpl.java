package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

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

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class ScheduleStatusServiceImpl implements ScheduleStatusService {

    private final ScheduleStatusMapper mapper;
    private final ScheduleStatusRepository repository;

    @Override
    public ScheduleStatusDto create(ScheduleStatusDto scheduleStatusDto) {
        ScheduleStatus request = mapper.toEntity(scheduleStatusDto);

        ScheduleStatus response = createEntity(request);

        return mapper.toDto(response);
    }

    @Override
    public ScheduleStatus createEntity(ScheduleStatus scheduleStatus) {
        try {
            return repository.save(scheduleStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sss-01", "schedule status", e.getLocalizedMessage());
        }
    }


    @Override
    public ScheduleStatusDto read(String id) {
        ScheduleStatus response = readEntity(id);

        return mapper.toDto(response);
    }


    @Override
    public ScheduleStatus readEntity(String id) {
        Optional<ScheduleStatus> responseOptional = repository.findById(id);
        if (responseOptional.isPresent()) {
            return responseOptional.get();
        } else {
            throw new NotFoundException("sss-02", "id", id);
        }
    }

    @Override
    public List<ScheduleStatusDto> search(ScheduleStatusDto filterDto, String[] sortBy,
                                          int pageSize, int pageNumber) {
        ScheduleStatus filter = mapper.toEntity(filterDto);

        List<ScheduleStatus> response = searchEntities(filter, sortBy, pageSize, pageNumber);

        return mapper.toDtoList(response);
    }

    @Override
    public List<ScheduleStatus> searchEntities(ScheduleStatus filter, String[] sortBy,
                                               int pageSize, int pageNumber) {
        List<Sort.Order> orders = Utils.ordersByStringArray(sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        return repository.search(
                filter.getType(),
                filter.getName(),
                pageable);
    }

    @Override
    public List<ScheduleStatus> searchEntities(ScheduleStatus filter,
                                               int pageSize, int pageNumber) {
        String sortByFromProperties = "${application.constant.defaultSort}";
        String[] sortBy;
        try {
            sortBy = sortByFromProperties.split(",");
        } catch (NullPointerException e) {
            throw new NotFoundException("sss-03", "defaultSort", null);
        }
        return searchEntities(filter, sortBy, pageSize, pageNumber);
    }


    @Override
    public ScheduleStatusDto update(String id, ScheduleStatusDto scheduleStatusDto) {
        ScheduleStatus request = mapper.toEntity(scheduleStatusDto);

        ScheduleStatus response = updateEntity(id, request);

        return mapper.toDto(response);
    }

    @Override
    public ScheduleStatus updateEntity(String id, ScheduleStatus scheduleStatus) {
        if (repository.existsById(id)) {
            scheduleStatus.setId(id);
            return repository.save(scheduleStatus);
        } else {
            throw new NotFoundException("sss-04", "id", id);
        }
    }


    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("sss-05", "id", id);
        }
    }

}
