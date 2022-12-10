package ru.zalimannard.bachelorthesisserver.application;

import java.util.List;

public interface ApplicationService {
    ApplicationDto get(int id);

    List<ApplicationDto> getAll();

    ApplicationDto post(ApplicationDto applicationDto);

    ApplicationDto put(ApplicationDto applicationDto);

    ApplicationDto delete(int id);
}
