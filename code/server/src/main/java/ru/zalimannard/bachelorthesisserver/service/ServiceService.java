package ru.zalimannard.bachelorthesisserver.service;

import java.util.List;

public interface ServiceService {
    ServiceDto read(int id);

    List<ServiceDto> list();
}
