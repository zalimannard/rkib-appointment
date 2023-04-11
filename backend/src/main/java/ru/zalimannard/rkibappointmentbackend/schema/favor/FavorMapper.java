package ru.zalimannard.rkibappointmentbackend.schema.favor;

public interface FavorMapper {

    Favor toEntity(FavorDto dto);

    FavorDto toDto(Favor entity);

}
