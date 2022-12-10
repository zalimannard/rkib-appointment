package ru.zalimannard.bachelorthesisserver.application.status;

import java.util.List;

public interface ApplicationStatusService {
    ApplicationStatusDto get(int id);

    List<ApplicationStatusDto> getAll();

    ApplicationStatusDto post(ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto put(ApplicationStatusDto applicationStatusDto);

    ApplicationStatusDto delete(int id);
}
