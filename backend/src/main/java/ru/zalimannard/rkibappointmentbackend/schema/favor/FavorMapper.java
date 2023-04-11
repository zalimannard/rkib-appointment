package ru.zalimannard.rkibappointmentbackend.schema.favor;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavorMapper {

    Favor toEntity(FavorDto dto);

    FavorDto toDto(Favor entity);

    List<Favor> toEntityList(List<FavorDto> dto);

    List<FavorDto> toDtoList(List<Favor> entity);

}
