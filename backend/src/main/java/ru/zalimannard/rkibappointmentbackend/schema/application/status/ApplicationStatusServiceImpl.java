package ru.zalimannard.rkibappointmentbackend.schema.application.status;

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
public class ApplicationStatusServiceImpl implements ApplicationStatusService {

    private final ApplicationStatusMapper mapper;
    private final ApplicationStatusRepository repository;

    @Override
    public ApplicationStatusDto create(ApplicationStatusDto applicationStatusDto) {
        ApplicationStatus request = mapper.toEntity(applicationStatusDto);

        ApplicationStatus response = createEntity(request);

        return mapper.toDto(response);
    }

    @Override
    public ApplicationStatus createEntity(ApplicationStatus applicationStatus) {
        try {
            return repository.save(applicationStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("aps-01", "application status", e.getLocalizedMessage());
        }
    }


    @Override
    public ApplicationStatusDto read(String id) {
        ApplicationStatus response = readEntity(id);

        return mapper.toDto(response);
    }


    @Override
    public ApplicationStatus readEntity(String id) {
        Optional<ApplicationStatus> responseOptional = repository.findById(id);
        if (responseOptional.isPresent()) {
            return responseOptional.get();
        } else {
            throw new NotFoundException("aps-02", "id", id);
        }
    }

    @Override
    public List<ApplicationStatusDto> search(ApplicationStatusDto filterDto, String[] sortBy,
                                             int pageSize, int pageNumber) {
        ApplicationStatus filter = mapper.toEntity(filterDto);

        List<ApplicationStatus> response = searchEntities(filter, sortBy, pageSize, pageNumber);

        return mapper.toDtoList(response);
    }

    @Override
    public List<ApplicationStatus> searchEntities(ApplicationStatus filter, String[] sortBy,
                                                  int pageSize, int pageNumber) {
        List<Sort.Order> orders = Utils.ordersByStringArray(sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        return repository.search(
                filter.getType(),
                filter.getName(),
                pageable);
    }

    @Override
    public List<ApplicationStatus> searchEntities(ApplicationStatus filter,
                                                  int pageSize, int pageNumber) {
        String sortByFromProperties = "${application.constant.defaultSort}";
        String[] sortBy;
        try {
            sortBy = sortByFromProperties.split(",");
        } catch (NullPointerException e) {
            throw new NotFoundException("aps-03", "defaultSort", null);
        }
        return searchEntities(filter, sortBy, pageSize, pageNumber);
    }


    @Override
    public ApplicationStatusDto update(String id, ApplicationStatusDto applicationStatusDto) {
        ApplicationStatus request = mapper.toEntity(applicationStatusDto);

        ApplicationStatus response = updateEntity(id, request);

        return mapper.toDto(response);
    }

    @Override
    public ApplicationStatus updateEntity(String id, ApplicationStatus applicationStatus) {
        if (repository.existsById(id)) {
            applicationStatus.setId(id);
            return repository.save(applicationStatus);
        } else {
            throw new NotFoundException("aps-04", "id", id);
        }
    }


    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("aps-05", "id", id);
        }
    }

}
