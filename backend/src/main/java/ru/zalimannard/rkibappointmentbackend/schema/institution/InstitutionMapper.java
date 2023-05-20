package ru.zalimannard.rkibappointmentbackend.schema.institution;

import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionResponseDto;

import java.util.List;

@Component
public class InstitutionMapper {

    public Institution toEntity(InstitutionRequestDto institutionRequestDto) {
        return Institution.builder()
                .name(institutionRequestDto.getName())
                .build();
    }

    public InstitutionResponseDto toDto(Institution institution) {
        return InstitutionResponseDto.builder()
                .id(institution.getId())
                .name(institution.getName())
                .build();
    }

    public List<InstitutionResponseDto> toDtoList(List<Institution> institutions) {
        return institutions.stream().map(this::toDto).toList();
    }

}
