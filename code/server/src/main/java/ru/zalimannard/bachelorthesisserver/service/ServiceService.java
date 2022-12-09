package ru.zalimannard.bachelorthesisserver.service;

import java.util.List;

public interface ServiceService {
    ServiceDto get(int id);

    List<ServiceDto> getAll();
}
