package ru.zalimannard.rkibappointmentbackend.schema.application.status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public interface ApplicationStatusService {

    ApplicationStatusDto create(ApplicationStatusDto applicationStatusDto);

    ApplicationStatus createEntity(@Valid ApplicationStatus applicationStatus);


    ApplicationStatusDto read(String id);

    ApplicationStatus readEntity(@NotNull String id);


    List<ApplicationStatusDto> search(ApplicationStatusDto filterDto, String[] sortBy,
                                      @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<ApplicationStatus> searchEntities(ApplicationStatus filter, String[] sortBy,
                                           @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<ApplicationStatus> searchEntities(ApplicationStatus filter,
                                           @Positive int pageSize, @PositiveOrZero int pageNumber);


    ApplicationStatusDto update(String id, @Valid ApplicationStatusDto applicationStatusDto);

    ApplicationStatus updateEntity(@NotBlank String id, @Valid ApplicationStatus applicationStatus);


    void delete(@NotBlank String id);

}
