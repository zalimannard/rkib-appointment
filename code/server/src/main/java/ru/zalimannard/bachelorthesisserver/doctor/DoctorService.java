package ru.zalimannard.bachelorthesisserver.doctor;

import java.util.List;

public interface DoctorService {
    DoctorDto get(int id);

    List<DoctorDto> getAll();
}
