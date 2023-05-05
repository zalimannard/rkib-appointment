package ru.zalimannard.rkibappointmentbackend.schema.person.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final PersonMapper personMapper;

    public Patient toEntity(PatientRequestDto patientRequestDto,
                            Person person) {
        return Patient.builder()
                .person(person)
                .phoneNumber(patientRequestDto.getPhoneNumber())
                .birthdate(patientRequestDto.getBirthdate())
                .address(patientRequestDto.getAddress())
                .occupation(patientRequestDto.getOccupation())
                .build();
    }

    public PatientResponseDto toDto(Patient patient) {
        return PatientResponseDto.builder()
                .id(patient.getId())
                .phoneNumber(patient.getPhoneNumber())
                .birthdate(patient.getBirthdate())
                .address(patient.getAddress())
                .occupation(patient.getOccupation())
                .person(personMapper.toDto(patient.getPerson()))
                .build();
    }

    public List<PatientResponseDto> toDtoList(List<Patient> patients) {
        return patients.stream().map(this::toDto).toList();
    }

}
