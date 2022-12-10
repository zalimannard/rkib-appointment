package ru.zalimannard.bachelorthesisserver.application;

public interface ApplicationMapper {
    Application toEntity(ApplicationDto dto);

    ApplicationDto toDto(Application entity);
}
