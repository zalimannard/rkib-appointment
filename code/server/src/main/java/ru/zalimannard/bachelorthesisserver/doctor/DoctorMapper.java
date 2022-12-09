package ru.zalimannard.bachelorthesisserver.doctor;

public interface DoctorMapper {
    Doctor toEntity(DoctorDto dto);

    DoctorDto toDto(Doctor entity);
}
