package ru.zalimannard.bachelorthesisserver.patient;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper = Mappers.getMapper(PatientMapper.class);

    @Override
    public PatientDto get(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient", "id", id));
        return patientMapper.toDto(patient);
    }

    @Override
    public List<PatientDto> list() {
        List<Patient> patientList = new ArrayList<>();
        patientRepository.findAll().forEach(patientList::add);
        return patientMapper.toDtoList(patientList);
    }

    @Override
    public PatientDto create(PatientDto patientDto) {
        Patient patientRequest = patientMapper.toEntity(patientDto);
        Patient patientResponse = patientRepository.save(patientRequest);
        return patientMapper.toDto(patientResponse);
    }

    @Override
    public PatientDto update(PatientDto patientDto) {
        Patient patientRequest = patientMapper.toEntity(patientDto);
        if (patientRepository.existsById(patientRequest.getId())) {
            Patient patientResponse = patientRepository.save(patientRequest);
            return patientMapper.toDto(patientResponse);
        } else {
            throw new NotFoundException("Patient", "id", String.valueOf(patientRequest.getId()));
        }
    }

    @Override
    public PatientDto delete(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient", "id", id));
        patientRepository.deleteById(id);
        return patientMapper.toDto(patient);
    }
}
