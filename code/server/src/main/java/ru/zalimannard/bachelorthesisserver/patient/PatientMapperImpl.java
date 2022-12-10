package ru.zalimannard.bachelorthesisserver.patient;

import org.springframework.stereotype.Component;

@Component
public class PatientMapperImpl implements PatientMapper {
    @Override
    public Patient toEntity(PatientDto dto) {
        return Patient.builder()
                .id(dto.getId())
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .phoneNumber(dto.getPhoneNumber())
                .birthdate(dto.getBirthdate())
                .address(dto.getAddress())
                .occupation(dto.getOccupation())
                .build();
    }

    @Override
    public PatientDto toDto(Patient entity) {
        return PatientDto.builder()
                .id(entity.getId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .phoneNumber(entity.getPhoneNumber())
                .birthdate(entity.getBirthdate())
                .address(entity.getAddress())
                .occupation(entity.getOccupation())
                .build();
    }
}
