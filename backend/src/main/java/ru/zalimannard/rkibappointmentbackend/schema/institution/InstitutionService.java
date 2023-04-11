package ru.zalimannard.rkibappointmentbackend.schema.institution;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public interface InstitutionService {

    InstitutionDto create(InstitutionDto institutionDto);

    Institution createEntity(@Valid Institution institution);


    InstitutionDto read(String id);

    Institution readEntity(@NotNull String id);


    List<InstitutionDto> search(InstitutionDto filterDto, String[] sortBy,
                                @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Institution> searchEntities(Institution filter, String[] sortBy,
                                     @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Institution> searchEntities(Institution filter,
                                     @Positive int pageSize, @PositiveOrZero int pageNumber);


    InstitutionDto update(String id, @Valid InstitutionDto institutionDto);

    Institution updateEntity(@NotBlank String id, @Valid Institution institution);


    void delete(@NotBlank String id);

}
