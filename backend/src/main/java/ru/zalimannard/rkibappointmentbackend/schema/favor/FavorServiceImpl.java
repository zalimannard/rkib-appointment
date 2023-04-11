package ru.zalimannard.rkibappointmentbackend.schema.favor;

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
public class FavorServiceImpl implements FavorService {

    private final FavorMapper mapper;
    private final FavorRepository repository;

    @Override
    public FavorDto create(FavorDto favorDto) {
        Favor request = mapper.toEntity(favorDto);

        Favor response = createEntity(request);

        return mapper.toDto(response);
    }

    @Override
    public Favor createEntity(Favor favor) {
        try {
            return repository.save(favor);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("fas-01", "favor", e.getLocalizedMessage());
        }
    }


    @Override
    public FavorDto read(String id) {
        Favor response = readEntity(id);

        return mapper.toDto(response);
    }


    @Override
    public Favor readEntity(String id) {
        Optional<Favor> responseOptional = repository.findById(id);
        if (responseOptional.isPresent()) {
            return responseOptional.get();
        } else {
            throw new NotFoundException("fas-02", "id", id);
        }
    }

    @Override
    public List<FavorDto> search(FavorDto filterDto, String[] sortBy,
                                 int pageSize, int pageNumber) {
        Favor filter = mapper.toEntity(filterDto);

        List<Favor> response = searchEntities(filter, sortBy, pageSize, pageNumber);

        return mapper.toDtoList(response);
    }

    @Override
    public List<Favor> searchEntities(Favor filter, String[] sortBy,
                                      int pageSize, int pageNumber) {
        List<Sort.Order> orders = Utils.ordersByStringArray(sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        return repository.search(
                filter.getName(),
                pageable);
    }

    @Override
    public List<Favor> searchEntities(Favor filter,
                                      int pageSize, int pageNumber) {
        String sortByFromProperties = "${application.constant.defaultSort}";
        String[] sortBy;
        try {
            sortBy = sortByFromProperties.split(",");
        } catch (NullPointerException e) {
            throw new NotFoundException("fas-03", "defaultSort", null);
        }
        return searchEntities(filter, sortBy, pageSize, pageNumber);
    }


    @Override
    public FavorDto update(String id, FavorDto favorDto) {
        Favor request = mapper.toEntity(favorDto);

        Favor response = updateEntity(id, request);

        return mapper.toDto(response);
    }

    @Override
    public Favor updateEntity(String id, Favor favor) {
        if (repository.existsById(id)) {
            favor.setId(id);
            return repository.save(favor);
        } else {
            throw new NotFoundException("fas-04", "id", id);
        }
    }


    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("fas-05", "id", id);
        }
    }

}
