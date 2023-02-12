package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UnscheduledVisitMapper {

    UnscheduledVisit toEntity(UnscheduledVisitDto dto);

    UnscheduledVisitDto toDto(UnscheduledVisit entity);

    List<UnscheduledVisit> toEntityList(List<UnscheduledVisitDto> dtoList);

    List<UnscheduledVisitDto> toDtoList(List<UnscheduledVisit> entityList);
}
