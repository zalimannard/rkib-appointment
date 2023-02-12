package ru.zalimannard.bachelorthesisserver.application;

import java.util.List;

public interface ApplicationService {

    ApplicationDto get(String id);

    List<ApplicationDto> list();

    ApplicationDto create(ApplicationDto applicationDto);

    ApplicationDto update(ApplicationDto applicationDto);

    ApplicationDto delete(String id);
}
