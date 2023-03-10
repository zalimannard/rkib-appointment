package ru.zalimannard.bachelorthesisserver.schema.doctor;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class DoctorMapper {

    public abstract Doctor toEntity(DoctorDto dto,
                                    @Context MappingType mappingType);

    public abstract DoctorDto toDto(Doctor entity);

    public abstract List<Doctor> toEntityList(List<DoctorDto> dtoList,
                                              @Context MappingType mappingType);

    public abstract List<DoctorDto> toDtoList(List<Doctor> entityList);

}
