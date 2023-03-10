package ru.zalimannard.bachelorthesisserver.schema.favor;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class FavorMapper {

    public abstract Favor toEntity(FavorDto dto);

    public abstract FavorDto toDto(Favor entity);

    public abstract List<Favor> toEntityList(List<FavorDto> dtoList);

    public abstract List<FavorDto> toDtoList(List<Favor> entityList);

}
