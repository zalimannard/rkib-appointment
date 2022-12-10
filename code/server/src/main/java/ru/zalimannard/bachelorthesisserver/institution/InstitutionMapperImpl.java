package ru.zalimannard.bachelorthesisserver.institution;

import org.springframework.stereotype.Component;

@Component
public class InstitutionMapperImpl implements InstitutionMapper {
    @Override
    public Institution toEntity(InstitutionDto dto) {
        return Institution.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public InstitutionDto toDto(Institution entity) {
        return InstitutionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
