package ru.zalimannard.bachelorthesisserver.doctornote;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DoctorNoteMapper {

    DoctorNote toEntity(DoctorNoteDto dto);

    DoctorNoteDto toDto(DoctorNote entity);

    List<DoctorNote> toEntityList(List<DoctorNoteDto> dtoList);

    List<DoctorNoteDto> toDtoList(List<DoctorNote> entityList);
}
