package ru.zalimannard.rkibappointmentbackend.schema.favor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public interface FavorService {

    FavorDto create(FavorDto favorDto);

    Favor createEntity(@Valid Favor favor);


    FavorDto read(String id);

    Favor readEntity(@NotNull String id);


    List<FavorDto> search(FavorDto filterDto, String[] sortBy,
                          @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Favor> searchEntities(Favor filter, String[] sortBy,
                               @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Favor> searchEntities(Favor filter,
                               @Positive int pageSize, @PositiveOrZero int pageNumber);


    FavorDto update(String id, @Valid FavorDto favorDto);

    Favor updateEntity(@NotBlank String id, @Valid Favor favor);


    void delete(@NotBlank String id);

}
