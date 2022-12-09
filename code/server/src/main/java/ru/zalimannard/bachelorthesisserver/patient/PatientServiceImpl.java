package ru.zalimannard.bachelorthesisserver.patient;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientMapper patientMapper, PatientRepository patientRepository) {
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDto get(int id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            return patientMapper.toDto(patient);
        } else {
            throw new NotFoundException("Пациент не найден");
        }
    }

    @Override
    public List<PatientDto> getAll() {
        Iterable<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientDtos = new ArrayList<>();
        patients.forEach(patient -> patientDtos.add(patientMapper.toDto(patient)));
        return patientDtos;
    }

    @Override
    public PatientDto post(PatientDto patientDto) {
        Patient patientToAdd = patientMapper.toEntity(patientDto);
        Patient createdPatient = patientRepository.save(patientToAdd);
        return patientMapper.toDto(createdPatient);
    }

    @Override
    public PatientDto put(PatientDto patientDto) {
        if (patientRepository.existsById(patientDto.getId())) {
            Patient patient = patientMapper.toEntity(patientDto);
            patientRepository.save(patient);
            return get(patientDto.getId());
        } else {
            throw new NotFoundException("Изменяемого пациента не существует");
        }
    }

    @Override
    public PatientDto delete(int id) {
        PatientDto patientDto = get(id);
        patientRepository.deleteById(id);
        return patientDto;
    }
}
