package ru.zalimannard.bachelorthesisserver.schema.application.status;

import java.util.List;

public interface ApplicationStatusService {

    ApplicationStatusDto get(String id);

    List<ApplicationStatusDto> list(ApplicationStatusDto exampleApplicationStatusDto);

    ApplicationStatusDto create(ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto update(ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto delete(String id);

}
