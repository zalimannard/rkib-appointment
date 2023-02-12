package ru.zalimannard.bachelorthesisserver.favor;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FavorMapper {

    Favor toEntity(FavorDto dto);

    FavorDto toDto(Favor entity);

    List<Favor> toEntityList(List<FavorDto> dtoList);

    List<FavorDto> toDtoList(List<Favor> entityList);
}
