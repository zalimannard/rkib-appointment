package ru.zalimannard.rkibappointmentbackend.schema.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public interface ApplicationService {

    ApplicationDto create(ApplicationDto applicationDto);

    Application createEntity(@Valid Application application);


    ApplicationDto read(String id);

    Application readEntity(@NotNull String id);


    List<ApplicationDto> search(ApplicationDto filterDto, String[] sortBy,
                                @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Application> searchEntities(Application filter, String[] sortBy,
                                     @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Application> searchEntities(Application filter,
                                     @Positive int pageSize, @PositiveOrZero int pageNumber);


    ApplicationDto update(String id, @Valid ApplicationDto applicationDto);

    Application updateEntity(@NotBlank String id, @Valid Application application);


    void delete(@NotBlank String id);

}
