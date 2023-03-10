package ru.zalimannard.bachelorthesisserver.schema.doctor;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class DoctorMapper {

    public abstract Doctor toEntity(DoctorDto dto);

    public abstract DoctorDto toDto(Doctor entity);

    public abstract List<Doctor> toEntityList(List<DoctorDto> dtoList);

    public abstract List<DoctorDto> toDtoList(List<Doctor> entityList);

}
