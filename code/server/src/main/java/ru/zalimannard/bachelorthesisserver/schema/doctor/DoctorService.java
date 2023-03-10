package ru.zalimannard.bachelorthesisserver.schema.doctor;

import java.util.List;

public interface DoctorService {

    DoctorDto get(String id);

    List<DoctorDto> list(DoctorDto exampleDoctorDto);

}
