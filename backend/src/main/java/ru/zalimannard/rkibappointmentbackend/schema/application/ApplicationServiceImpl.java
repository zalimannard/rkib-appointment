package ru.zalimannard.rkibappointmentbackend.schema.application;

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
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationMapper mapper;
    private final ApplicationRepository repository;

    @Override
    public ApplicationDto create(ApplicationDto applicationDto) {
        Application request = mapper.toEntity(applicationDto);

        Application response = createEntity(request);

        return mapper.toDto(response);
    }

    @Override
    public Application createEntity(Application application) {
        try {
            return repository.save(application);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("aps-01", "application", e.getLocalizedMessage());
        }
    }


    @Override
    public ApplicationDto read(String id) {
        Application response = readEntity(id);

        return mapper.toDto(response);
    }


    @Override
    public Application readEntity(String id) {
        Optional<Application> responseOptional = repository.findById(id);
        if (responseOptional.isPresent()) {
            return responseOptional.get();
        } else {
            throw new NotFoundException("aps-02", "id", id);
        }
    }

    @Override
    public List<ApplicationDto> search(ApplicationDto filterDto, String[] sortBy,
                                       int pageSize, int pageNumber) {
        Application filter = mapper.toEntity(filterDto);

        List<Application> response = searchEntities(filter, sortBy, pageSize, pageNumber);

        return mapper.toDtoList(response);
    }

    @Override
    public List<Application> searchEntities(Application filter, String[] sortBy,
                                            int pageSize, int pageNumber) {
        List<Sort.Order> orders = Utils.ordersByStringArray(sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        return repository.search(
                filter.getPatient(),
                filter.getSendingInstitution(),
                filter.getStatus(),
                filter.getDoctorNote(),
                filter.getFinalDiagnosis(),
                filter.getCommentary(),
                pageable);
    }

    @Override
    public List<Application> searchEntities(Application filter,
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
    public ApplicationDto update(String id, ApplicationDto applicationDto) {
        Application request = mapper.toEntity(applicationDto);

        Application response = updateEntity(id, request);

        return mapper.toDto(response);
    }

    @Override
    public Application updateEntity(String id, Application application) {
        if (repository.existsById(id)) {
            application.setId(id);
            return repository.save(application);
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
