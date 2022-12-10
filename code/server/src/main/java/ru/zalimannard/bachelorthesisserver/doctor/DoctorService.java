package ru.zalimannard.bachelorthesisserver.doctor;

import java.util.List;

public interface DoctorService {
    DoctorDto read(int id);

    List<DoctorDto> list();
}
