package ru.zalimannard.bachelorthesisserver.application.status;

import java.util.List;

public interface ApplicationStatusService {

    ApplicationStatusDto get(int id);

    List<ApplicationStatusDto> list();

    ApplicationStatusDto create(ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto update(ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto delete(int id);
}
