package ru.zalimannard.bachelorthesisserver.schema.doctor;

import java.util.List;

public interface DoctorService {

    DoctorDto read(String id);

    List<DoctorDto> search(DoctorDto filterDoctorDto, int pageNo, int pageSize, String[] sort);

}
