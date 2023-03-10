package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ScheduleStatusMapper {

    public abstract ScheduleStatus toEntity(ScheduleStatusDto dto,
                                            @Context MappingType mappingType);

    public abstract ScheduleStatusDto toDto(ScheduleStatus entity);

    public abstract List<ScheduleStatus> toEntityList(List<ScheduleStatusDto> dtoList,
                                                      @Context MappingType mappingType);

    public abstract List<ScheduleStatusDto> toDtoList(List<ScheduleStatus> entityList);

}
