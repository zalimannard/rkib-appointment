package ru.zalimannard.bachelorthesisserver.schema.application;

import jakarta.validation.Valid;

import java.util.List;

public interface ApplicationService {

    ApplicationDto create(@Valid ApplicationDto applicationDto);

    ApplicationDto read(String id);

    List<ApplicationDto> search(ApplicationDto filterApplicationDto, int pageNo, int pageSize, String[] sortBy);

    ApplicationDto update(String id, @Valid ApplicationDto applicationDto);

    ApplicationDto delete(String id);

}
