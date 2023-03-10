package ru.zalimannard.bachelorthesisserver.schema.application.status;

import jakarta.validation.Valid;

import java.util.List;

public interface ApplicationStatusService {

    ApplicationStatusDto create(@Valid ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto read(String id);

    List<ApplicationStatusDto> search(ApplicationStatusDto filterApplicationStatusDto, int pageNo, int pageSize, String[] sortBy);

    ApplicationStatusDto update(String id, @Valid ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto delete(String id);

}
