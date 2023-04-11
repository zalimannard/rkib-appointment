package ru.zalimannard.rkibappointmentbackend.schema.institution;

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
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionMapper mapper;
    private final InstitutionRepository repository;

    @Override
    public InstitutionDto create(InstitutionDto institutionDto) {
        Institution request = mapper.toEntity(institutionDto);

        Institution response = createEntity(request);

        return mapper.toDto(response);
    }

    @Override
    public Institution createEntity(Institution institution) {
        try {
            return repository.save(institution);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("ins-01", "institution", e.getLocalizedMessage());
        }
    }


    @Override
    public InstitutionDto read(String id) {
        Institution response = readEntity(id);

        return mapper.toDto(response);
    }


    @Override
    public Institution readEntity(String id) {
        Optional<Institution> responseOptional = repository.findById(id);
        if (responseOptional.isPresent()) {
            return responseOptional.get();
        } else {
            throw new NotFoundException("ins-02", "id", id);
        }
    }

    @Override
    public List<InstitutionDto> search(InstitutionDto filterDto, String[] sortBy,
                                       int pageSize, int pageNumber) {
        Institution filter = mapper.toEntity(filterDto);

        List<Institution> response = searchEntities(filter, sortBy, pageSize, pageNumber);

        return mapper.toDtoList(response);
    }

    @Override
    public List<Institution> searchEntities(Institution filter, String[] sortBy,
                                            int pageSize, int pageNumber) {
        List<Sort.Order> orders = Utils.ordersByStringArray(sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        return repository.search(
                filter.getName(),
                pageable);
    }

    @Override
    public List<Institution> searchEntities(Institution filter,
                                            int pageSize, int pageNumber) {
        String sortByFromProperties = "${application.constant.defaultSort}";
        String[] sortBy;
        try {
            sortBy = sortByFromProperties.split(",");
        } catch (NullPointerException e) {
            throw new NotFoundException("ins-03", "defaultSort", null);
        }
        return searchEntities(filter, sortBy, pageSize, pageNumber);
    }


    @Override
    public InstitutionDto update(String id, InstitutionDto institutionDto) {
        Institution request = mapper.toEntity(institutionDto);

        Institution response = updateEntity(id, request);

        return mapper.toDto(response);
    }

    @Override
    public Institution updateEntity(String id, Institution institution) {
        if (repository.existsById(id)) {
            institution.setId(id);
            return repository.save(institution);
        } else {
            throw new NotFoundException("ins-04", "id", id);
        }
    }


    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("ins-05", "id", id);
        }
    }

}
