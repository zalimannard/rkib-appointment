package ru.zalimannard.bachelorthesisserver.schema.favor;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class FavorMapper {

    public abstract Favor toEntity(FavorDto dto,
                                   @Context MappingType mappingType);

    public abstract FavorDto toDto(Favor entity);

    public abstract List<Favor> toEntityList(List<FavorDto> dtoList,
                                             @Context MappingType mappingType);

    public abstract List<FavorDto> toDtoList(List<Favor> entityList);

}
