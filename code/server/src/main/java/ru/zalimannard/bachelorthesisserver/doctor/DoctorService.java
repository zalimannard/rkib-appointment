package ru.zalimannard.bachelorthesisserver.doctor;

import java.util.List;

public interface DoctorService {

    DoctorDto get(String id);

    List<DoctorDto> list();
}
