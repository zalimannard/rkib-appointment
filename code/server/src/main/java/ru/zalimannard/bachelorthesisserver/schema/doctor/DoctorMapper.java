package ru.zalimannard.bachelorthesisserver.schema.doctor;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DoctorMapper {

    Doctor toEntity(DoctorDto dto);

    DoctorDto toDto(Doctor entity);

    List<Doctor> toEntityList(List<DoctorDto> dtoList);

    List<DoctorDto> toDtoList(List<Doctor> entityList);
}
