package ru.zalimannard.bachelorthesisserver.doctor;

import org.springframework.stereotype.Component;

@Component
public class DoctorMapperImpl implements DoctorMapper {
    @Override
    public Doctor toEntity(DoctorDto dto) {
        return Doctor.builder()
                .id(dto.getId())
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .build();
    }

    @Override
    public DoctorDto toDto(Doctor entity) {
        return DoctorDto.builder()
                .id(entity.getId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .build();
    }
}
